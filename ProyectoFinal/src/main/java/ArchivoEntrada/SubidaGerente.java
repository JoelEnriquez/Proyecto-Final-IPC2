/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;
import ConexionDB.Conexion;
import Encriptacion.Encriptar;
import EntidadesUsuario.Empleado;
import EntidadesUsuario.Usuario;
import SubidaDB.GuardarUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author joel
 */
public class SubidaGerente {
    
    private Connection conexion = Conexion.getConexion();
    private Encriptar encriptar;
    private LecturaXML lecturaXML;
    private List<Element> listaGerentes;
    private GuardarUsuario guardarGerente;

    public SubidaGerente(List<Element> listaGerentes, LecturaXML lecturaXML) {
        this.listaGerentes = listaGerentes;
        encriptar = new Encriptar();
        this.lecturaXML = lecturaXML;
        guardarGerente = new GuardarUsuario();
    }
    
    public void ejecutarSubida() throws Exception{
        for (int i = 0; i < listaGerentes.size(); i++) {
            Element gerente = listaGerentes.get(i);
            
            String codigoGerente = gerente.getChildTextTrim("CODIGO");
            String nombreGerente =  gerente.getChildTextTrim("NOMBRE");
            String turnoGerente =  gerente.getChildTextTrim("TURNO");
            String noDPIGerente =  gerente.getChildTextTrim("DPI");
            String direccionGerente =  gerente.getChildTextTrim("DIRECCION");
            String sexoGerente =  gerente.getChildTextTrim("SEXO");
            String passGerente =  encriptar.encriptar(gerente.getChildTextTrim("PASSWORD"));
            
            Empleado nuevoGerente = construirGerente(codigoGerente, nombreGerente, noDPIGerente, direccionGerente, sexoGerente, passGerente, "Gerente",turnoGerente);
            insertarNuevoUsuario(nuevoGerente);
            insertarNuevoGerente(nuevoGerente);
        }   
    }
    
   
    private Empleado construirGerente(String codigoGerente, String nombreGerente, String noDPIGerente, String direccionGerente,String sexoGerente,String passGerente,String tipoEmpleado,String turnoGerente){
        String codigoTurno = "";
        
        if (turnoGerente.equals("MATUTINO")) {
            codigoTurno = "1";
        }
        else if (turnoGerente.equals("VESPERTINO")) {
            codigoTurno = "2";
        }
        
        return new Empleado(codigoGerente, nombreGerente, noDPIGerente, direccionGerente, sexoGerente, passGerente, tipoEmpleado, codigoTurno);      
    }
    
    private void insertarNuevoUsuario(Empleado empleado){
        String query  = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(empleado.getCodigo()));
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getDPI());
            ps.setString(4, empleado.getDireccion());
            ps.setString(5, empleado.getSexo());
            ps.setString(6, empleado.getPassword());
            ps.setString(7, empleado.getTipoUsuario());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private void insertarNuevoGerente(Empleado empleado){
        String query  = "INSERT INTO EMPLEADO VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(empleado.getCodigo()));
            ps.setString(2, empleado.getTipoUsuario());
            ps.setString(3, empleado.getCodigoTurno());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    
    
}
