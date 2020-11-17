/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import ConexionDB.Conexion;
import Encriptacion.Encriptar;
import EntidadesBanco.Transaccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author joel
 */
public class SubidaTransaccion {

    private Connection conexion = Conexion.getConexion();
    private Encriptar encriptar;
    private List<Element> listaTransacciones;

    public SubidaTransaccion(List<Element> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
        encriptar = new Encriptar();
    }
    
    public void ejecutarSubida(){
        for (int i = 0; i < listaTransacciones.size(); i++) {
            Element transaccion = listaTransacciones.get(i);
            
            String codigoTransaccion = transaccion.getChildTextTrim("CODIGO");
            String codigoCliente =  transaccion.getChildTextTrim("CUENTA-ID");
            Date fechaTransaccion =  Date.valueOf(transaccion.getChildTextTrim("FECHA"));
            Time horaTransaccion =  Time.valueOf(transaccion.getChildTextTrim("HORA"));
            String tipoTransaccion =  transaccion.getChildTextTrim("TIPO");
            double montoTransaccion =  Double.valueOf(transaccion.getChildTextTrim("MONTO"));
            String codigoCajero =  transaccion.getChildTextTrim("CAJERO-ID");
            
            Transaccion nuevaTransaccion = new Transaccion(codigoTransaccion, fechaTransaccion, horaTransaccion, tipoTransaccion, montoTransaccion, codigoCliente, codigoCajero);
            insertarNuevaTransaccion(nuevaTransaccion);
        }   
    }
    
  
    private void insertarNuevaTransaccion(Transaccion nuevaTransaccion){
        String query  = "INSERT INTO TRANSACCION (codigo, fecha, hora, tipo, monto, codigo_cuenta, codigo_cajero) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(nuevaTransaccion.getCodigo()));
            ps.setDate(2, nuevaTransaccion.getFecha());
            ps.setTime(3, nuevaTransaccion.getHora());
            ps.setString(4, nuevaTransaccion.getTipo());
            ps.setDouble(5, nuevaTransaccion.getMonto());
            ps.setInt(6, Integer.parseInt(nuevaTransaccion.getCodigoCuenta()));
            ps.setInt(7, Integer.parseInt(nuevaTransaccion.getCodigoCajero()));
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);   
        }
    }
    
    
}
