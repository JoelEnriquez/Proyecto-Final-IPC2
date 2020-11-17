/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorGerente;

/**
 *
 * @author joel
 */
public class ReportesGerente {
    
    private String HISTORIAL_CAMBIOS = "SELECT hc.* from HISTORIAL_CAMBIOS hc inner join USUARIO u on u.codigo = hc.codigo_usuario AND u.tipo_usuario = ?";
    private String TRANSACCION_MONETARIAS_MAYORES_LIMITE = "SELECT us.codigo, us.nombre, us.DPI, us.direccion, us.sexo, cl.birth, tr.monto from CLIENTE cl inner join CUENTA cu on cl.codigo_cliente = cu.codigo_cliente inner join TRANSACCION tr on cu.codigo = tr.codigo_cuenta inner join USUARIO us on us.codigo = cl.codigo_cliente where tr.monto > ?";
}
