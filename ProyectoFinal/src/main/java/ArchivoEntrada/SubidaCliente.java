/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import ConexionDB.Conexion;
import Encriptacion.Encriptar;
import EntidadesBanco.Cuenta;
import EntidadesUsuario.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author joel
 */
public class SubidaCliente {
    
    private Connection conexion = Conexion.getConexion();
    private Encriptar encriptar;
    private String pathArchivos;
    private List<Element> listaClientes;
    private ArrayList<Cuenta> cuentasCliente;

    public SubidaCliente(List<Element> listaClientes, String pathArchivos) {
        this.listaClientes = listaClientes;
        this.pathArchivos = pathArchivos;
        encriptar = new Encriptar();
        cuentasCliente = new ArrayList<>();
    }
    
    public void ejecutarSubida() throws Exception{
        for (int i = 0; i < listaClientes.size(); i++) {
            Element cajero = listaClientes.get(i);
            
            String codigoCliente = cajero.getChildTextTrim("CODIGO");
            String nombreCliente =  cajero.getChildTextTrim("NOMBRE");
            String noDPICliente =  cajero.getChildTextTrim("DPI");
            Date birthCliente =  Date.valueOf(cajero.getChildTextTrim("BIRTH"));
            String direccionCliente =  cajero.getChildTextTrim("DIRECCION");
            String sexoCliente =  cajero.getChildTextTrim("SEXO");
            //Cuentas
            List<Element> listaCuentas = cajero.getChildren("CUENTAS");
            for (int j = 0; j < listaCuentas.size(); j++) {         
                Element valoresCuenta = listaCuentas.get(j);
                //Valores de cada elemento de cuenta
                String codigoCuenta = valoresCuenta.getChildTextTrim("CODIGO");
                Date fechaCreacion =  Date.valueOf(valoresCuenta.getChildTextTrim("CREADA"));
                double montoCuenta =  Double.parseDouble(valoresCuenta.getChildTextTrim("CREDITO"));
                
                cuentasCliente.add(new Cuenta(codigoCuenta, fechaCreacion, montoCuenta, codigoCliente));
            }
            String nombrePDF_Cliente = cajero.getChildTextTrim("DPI-PDF");
            //Creamos el ImputStream en base al path
            File archivoPDF = new File(pathArchivos+nombrePDF_Cliente);
            InputStream PDF_Cliente = new FileInputStream(archivoPDF);
            
            String passCliente =  encriptar.encriptar(cajero.getChildTextTrim("PASSWORD"));
            
            Cliente nuevoCliente = new Cliente(codigoCliente, nombreCliente, noDPICliente, direccionCliente, sexoCliente, passCliente, "Cliente", birthCliente, PDF_Cliente);
            insertarNuevoUsuario(nuevoCliente);
            insertarNuevoCliente(nuevoCliente);
            
            //Agregamos todas las cuentas del Cliente
            for (Cuenta cuentaCliente : cuentasCliente) {
                insertarCuentasCliente(cuentaCliente);
            }
        }   
    }
    
    private void insertarNuevoUsuario(Cliente nuevoUsuario){
        String query  = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(nuevoUsuario.getCodigo()));
            ps.setString(2, nuevoUsuario.getNombre());
            ps.setString(3, nuevoUsuario.getDPI());
            ps.setString(4, nuevoUsuario.getDireccion());
            ps.setString(5, nuevoUsuario.getSexo());
            ps.setString(6, nuevoUsuario.getPassword());
            ps.setString(7, nuevoUsuario.getTipoUsuario());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private void insertarNuevoCliente(Cliente nuevoCliente){
        String query  = "INSERT INTO CLIENTE VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(nuevoCliente.getCodigo()));
            ps.setDate(2, nuevoCliente.getBirth());
            ps.setBlob(3, nuevoCliente.getPDF_DPI());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private void insertarCuentasCliente(Cuenta nuevaCuenta){
        String query  = "INSERT INTO CUENTA VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(nuevaCuenta.getCodigo()));
            ps.setDate(2, nuevaCuenta.getFechaCreacion());
            ps.setDouble(3, nuevaCuenta.getMonto());
            ps.setInt(4, Integer.parseInt(nuevaCuenta.getCodigoCliente()));
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
