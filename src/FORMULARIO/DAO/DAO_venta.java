/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.DAO;

import BASEDATO.LOCAL.ConnPostgres;
import BASEDATO.EvenConexion;
import CONFIGURACION.EvenDatosPc;
import Evento.Fecha.EvenFecha;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Jtable.EvenRender;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.BO_venta;
import FORMULARIO.ENTIDAD.cliente;
import FORMULARIO.ENTIDAD.venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JTable;

/**
 *
 * @author Digno
 */
public class DAO_venta {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenRender everende = new EvenRender();
    EvenFecha evefec = new EvenFecha();
    EvenDatosPc evepc = new EvenDatosPc();
    EvenJasperReport rep = new EvenJasperReport();
    venta ven = new venta();
//    BO_venta bo_ven=new BO_venta();
    public static String estado_ven_EMITIDO = "EMITIDO";
    public static String estado_ven_ANULADO = "ANULADO";
    public static String estado_ven_ANULADO_temp = "ANULADO_temp";
    public static String estado_ven_TERMINADO = "TERMINADO";
    private String mensaje_insert = "VENTA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "VENTA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.venta(\n"
            + "            idventa, fecha_inicio, fecha_fin, tipo_entrega, estado, monto_venta_efectivo, \n"
            + "            monto_delivery, delivery, observacion, comanda, equipo, fk_idcliente, \n"
            + "            fk_idusuario, fk_identregador,indice,nombre_mesa,forma_pago,monto_venta_tarjeta,monto_venta_total)\n"
            + "    VALUES (?, ?, ?, ?, ?, ?, \n"
            + "            ?, ?, ?, ?, ?, ?, \n"
            + "            ?, ?, ?, ?, ?, ?,?);";
    String restadeHoraTerminado = " ((((date_part('hour', v.fecha_fin))*60)+(date_part('minute', v.fecha_fin))) - \n"
            + "(((date_part('hour', v.fecha_inicio))*60)+(date_part('minute', v.fecha_inicio)))) ";
    private String sql_update_estado = "UPDATE public.venta\n"
            + "   SET fecha_fin=?,estado=?\n"
            + " WHERE idventa=?;";
    private String sql_cambiar_monto = "UPDATE public.venta\n"
            + "   SET monto_venta_efectivo=?,monto_venta_tarjeta=?\n"
            + " WHERE idventa=?;";
    private String sql_entregador = "UPDATE public.venta\n"
            + "   SET fk_identregador=?\n"
            + " WHERE indice=?;";
    private String sql_p_cocina = "UPDATE public.venta\n"
            + "   SET fecha_inicio=?,fecha_fin=?,estado=?\n"
            + " WHERE indice=?;";
    private String sql_est_ser = "UPDATE public.venta\n"
            + "   SET estado=?\n"
            + " WHERE indice=?;";
    private String orden = " order by v.idventa desc";
    private String mensaje_terminar = "TERMINADO";
    private String mensaje_entregador = "CAMBIO ENTREGADOR";
    private String mensaje_pasa_cocina = "PASAR A COCINA";
    private String sql_cargar = "SELECT  idventa,fecha_inicio, fecha_fin, tipo_entrega, estado, monto_venta_efectivo, \n"
            + "       monto_delivery, delivery, observacion, comanda, equipo, fk_idcliente, \n"
            + "       fk_idusuario, fk_identregador,indice,monto_venta_tarjeta \n"
            + "  FROM public.venta where idventa=";

    //5,10,5,20,10,10,10,10,10,10
    public void insertar_venta(Connection conn, venta ven) {
        int idventa = (eveconn.getInt_ultimoID_mas_uno(conn, ven.getTabla(), ven.getIdtabla()));
        ven.setC1idventa_estatico(idventa);
        ven.setC1idventa(idventa);
        String titulo = "insertar_venta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ven.getC1idventa());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setTimestamp(3, evefec.getTimestamp_sistema());
            pst.setString(4, ven.getC4tipo_entrega());
            pst.setString(5, ven.getC5estado());
            pst.setDouble(6, ven.getC6monto_venta_efectivo());
            pst.setDouble(7, ven.getC7monto_delivery());
            pst.setBoolean(8, ven.isC8delivery());
            pst.setString(9, ven.getC9observacion());
            pst.setString(10, ven.getC10comanda());
            pst.setString(11, ven.getC11equipo());
            pst.setInt(12, ven.getC12fk_idcliente());
            pst.setInt(13, ven.getC13fk_idusuario());
            pst.setInt(14, ven.getC14fk_identregador());
            pst.setString(15, ven.getC15indice());
            pst.setString(16, ven.getC16nombre_mesa());
            pst.setString(17, ven.getC17forma_pago());
            pst.setDouble(18, ven.getC18monto_venta_tarjeta());
            pst.setDouble(19, ven.getC19monto_venta_total());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ven.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (SQLException e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ven.toString(), titulo);
        }
    }

    public void update_estado_venta(Connection conn, venta ven) {
        String titulo = "update_estado_venta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_estado);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, ven.getC5estado());
            pst.setInt(3, ven.getC1idventa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_estado + "\n" + ven.toString(), titulo);
            evemen.modificado_correcto(mensaje_terminar, false);
        } catch (SQLException e) {
            evemen.mensaje_error(e, sql_update_estado + "\n" + ven.toString(), titulo);
        }
    }
    public void cambiar_monto_pago_venta(Connection conn, venta ven) {
        String titulo = "cambiar_monto_pago_venta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_cambiar_monto);
//            pst.setString(1, ven.getC17forma_pago());
            pst.setDouble(1, ven.getC6monto_venta_efectivo());
            pst.setDouble(2, ven.getC18monto_venta_tarjeta());
            pst.setInt(3, ven.getC1idventa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_cambiar_monto + "\n" + ven.toString(), titulo);
            evemen.modificado_correcto(mensaje_terminar, false);
        } catch (SQLException e) {
            evemen.mensaje_error(e, sql_cambiar_monto + "\n" + ven.toString(), titulo);
        }
    }
    public void update_cambio_entregador(Connection conn, venta ven) {
        String titulo = "update_cambio_entregador";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_entregador);
            pst.setInt(1, ven.getC14fk_identregador());
            pst.setString(2, ven.getC15indice());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_entregador + "\n" + ven.toString(), titulo);
            evemen.modificado_correcto(mensaje_entregador, false);
        } catch (SQLException e) {
            evemen.mensaje_error(e, sql_entregador + "\n" + ven.toString(), titulo);
        }
    }

    public void update_estado_pasar_cocina(Connection conn, venta ven) {
        String titulo = "update_estado_pasar_cocina";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_p_cocina);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, ven.getC5estado());
            pst.setString(4, ven.getC15indice());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_p_cocina + "\n" + ven.toString(), titulo);
            evemen.modificado_correcto(mensaje_pasa_cocina, false);
        } catch (SQLException e) {
            evemen.mensaje_error(e, sql_p_cocina + "\n" + ven.toString(), titulo);
        }
    }
    public void BO_update_estado_venta_servidor(Connection conn, String indice, String estado) {
        String titulo = "update_estado_venta_servidor";
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            update_estado_venta_servidor(conn, indice, estado);
            conn.commit();
        } catch (SQLException e) {
            evemen.mensaje_error(e, indice, titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evemen.Imprimir_serial_sql_error(e1, indice, titulo);
            }
        }
    }
    private void update_estado_venta_servidor(Connection conn, String indice, String estado) {
        String titulo = "update_estado_venta_servidor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_est_ser);
            pst.setString(1, estado);
            pst.setString(2, indice);
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_est_ser + "\n", titulo);
        } catch (SQLException e) {
            evemen.mensaje_error(e, sql_est_ser + "\n", titulo);
        }
    }

    public void actualizar_tabla_venta(Connection conn, JTable tblventa, String filtro, String campo_filtro) {
        String sql_select = "select v.idventa,v.indice,to_char(v.fecha_inicio,'yyyy-MM-dd HH24:MI') as fecha,\n"
                + "" + restadeHoraTerminado + " as tiempo,v.nombre_mesa as mesa,\n"
                + campo_filtro + ",\n"
                + "c.telefono,v.estado,v.tipo_entrega,v.forma_pago as pago,\n"
                + "TRIM(to_char((v.monto_venta_efectivo),'999G999G999')) as v_efectivo,\n"
                + "TRIM(to_char((v.monto_venta_tarjeta),'999G999G999')) as v_tarjeta,\n"
                + "TRIM(to_char(v.monto_delivery,'999G999G999')) as delivery, \n"
                + "TRIM(to_char((v.monto_venta_efectivo+v.monto_venta_tarjeta+v.monto_venta_total),'999G999G999')) as total\n"
                + "from venta v,cliente c,entregador e\n"
                + "where v.fk_idcliente=c.idcliente "
                + "and v.fk_identregador=e.identregador\n"
                + " " + filtro + "  order by v.idventa desc";
        eveconn.Select_cargar_jtable(conn, sql_select, tblventa);
        ancho_tabla_venta(tblventa);
        everende.rendertabla_estados(tblventa, 7);
    }

    public void cargar_venta(venta ven, int idventa) {
        String titulo = "cargar_venta";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idventa, titulo);
            if (rs.next()) {
                ven.setC1idventa(rs.getInt(1));
                ven.setC1idventa_estatico(rs.getInt(1));
                ven.setC2fecha_inicio(rs.getString(2));
                ven.setC3fecha_fin(rs.getString(3));
                ven.setC4tipo_entrega(rs.getString(4));
                ven.setC5estado(rs.getString(5));
                ven.setC6monto_venta_efectivo(rs.getDouble(6));
                ven.setC7monto_delivery(rs.getDouble(7));
                ven.setC8delivery(rs.getBoolean(8));
                ven.setC9observacion(rs.getString(9));
                ven.setC10comanda(rs.getString(10));
                ven.setC11equipo(rs.getString(11));
                ven.setC12fk_idcliente(rs.getInt(12));
                ven.setC12fk_idcliente_estatico(rs.getInt(12));
                ven.setC13fk_idusuario(rs.getInt(13));
                ven.setC14fk_identregador(rs.getInt(14));
                ven.setC15indice(rs.getString(15));
                ven.setC18monto_venta_tarjeta(rs.getDouble(16));
                ven.setMonto_ventaGlobal(rs.getDouble(6)+rs.getDouble(16));
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ven.toString(), titulo);
        }
    }

    public void ancho_tabla_venta(JTable tblventa) {
        int Ancho[] = {6, 2, 10, 5, 7, 16, 8, 8,8, 7,6,6,6,6};
        evejt.setAnchoColumnaJtable(tblventa, Ancho);
//        evejt.alinear_derecha_columna(tblventa, 10);
    }

    public void venta_terminado_hoy(Connection connLocal, Connection connServi) {
        String sql = "select indice,estado from venta where  "
                + " equipo='" + evepc.getString_nombre_pc() + "' "
                + "and date(fecha_inicio)=date('now()')";
        try {
            PreparedStatement pst = connServi.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            evemen.Imprimir_serial_sql(sql, "venta_terminado_hoy");
            while (rs.next()) {
                String indice = rs.getString("indice");
                String estado = rs.getString("estado");
                BO_update_estado_venta_servidor(connLocal, indice, estado);
//                bo_ven.update_estado_venta_servidor(connLocal, indice, estado);
//                update_estado_venta_servidor(connLocal, indice, estado);
            }
        } catch (SQLException e) {
            evemen.Imprimir_serial_sql_error(e, sql, "venta_terminado_hoy");
        }
    }

    public void imprimir_rep_venta_todos(Connection conn, String filtro) {
        String sql = "select v.idventa as idventa,to_char(v.fecha_inicio,'yyyy-MM-dd HH24:MI') as fecha,v.nombre_mesa as mesa,\n"
                + "c.ruc as ruc,('('||c.idcliente||')'||c.nombre) as cliente,c.tipo,\n"
                + "v.estado as estado,(v.monto_venta_efectivo+v.monto_venta_tarjeta+v.monto_venta_total) as monto,v.monto_delivery as delivery,u.usuario as usuario\n"
                + "from venta v,cliente c,usuario u\n"
                + "where v.fk_idcliente=c.idcliente\n"
                + "and v.fk_idusuario=u.idusuario\n"
                + " " + filtro + "\n"
                + "order by v.idventa desc;";
        String titulonota = "VENTA TODOS";
        String direccion = "src/REPORTE/VENTA/repVentaTodos.jrxml";
        rep.imprimirjasper(conn, sql, titulonota, direccion);
    }

    public double getDouble_suma_venta(Connection conn, String campo, String filtro) {
        double sumaventa = 0;
        String titulo = "getDouble_suma_venta";
        String sql = "select count(*) as cantidad,sum(v.monto_venta_efectivo+v.monto_venta_tarjeta+v.monto_venta_total) as sumaventa\n"
                + "from venta v,cliente c,usuario u\n"
                + "where v.fk_idcliente=c.idcliente\n"
                + "and v.fk_idusuario=u.idusuario\n"
                + " " + filtro + "\n"
                + " ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            evemen.Imprimir_serial_sql(sql, titulo);
            if (rs.next()) {
                sumaventa = rs.getDouble(campo);
            }
        } catch (SQLException e) {
            evemen.Imprimir_serial_sql_error(e, sql, titulo);
        }
        return sumaventa;
    }

    public void imprimir_rep_venta_detalle(Connection conn, String filtro) {
        String sql = "select v.idventa as idventa,to_char(v.fecha_inicio,'yyyy-MM-dd HH24:MI') as fecha,\n"
                + "v.nombre_mesa as mesa,v.estado as estado,\n"
                + "('('||c.idcliente||')'||c.nombre) as cliente,c.tipo as tipo,\n"
                + "('('||iv.tipo||')'||iv.descripcion) producto,\n"
                + "iv.cantidad as cant,(iv.cantidad*iv.precio_venta) as monto,\n"
                + "u.usuario as usuario\n"
                + "from venta v,cliente c,usuario u,item_venta iv\n"
                + "where v.fk_idcliente=c.idcliente\n"
                + "and v.fk_idusuario=u.idusuario\n"
                + "and v.idventa=iv.fk_idventa\n"
                + " " + filtro + "\n"
                + "order by iv.iditem_venta asc;";
        String titulonota = "VENTA DETALLE";
        String direccion = "src/REPORTE/VENTA/repVentaDetalle.jrxml";
        rep.imprimirjasper(conn, sql, titulonota, direccion);
    }

    public double getDouble_suma_venta_detalle(Connection conn, String campo, String filtro) {
        double sumaventa = 0;
        String titulo = "getDouble_suma_venta";
        String sql = "select count(*) as cantidad,sum(iv.cantidad*iv.precio_venta) as sumaventa\n"
                + "from venta v,cliente c,usuario u,item_venta iv\n"
                + "where v.fk_idcliente=c.idcliente\n"
                + "and v.fk_idusuario=u.idusuario\n"
                + "and v.idventa=iv.fk_idventa\n"
                + " " + filtro + "\n"
                + " ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            evemen.Imprimir_serial_sql(sql, titulo);
            if (rs.next()) {
                sumaventa = rs.getDouble(campo);
            }
        } catch (SQLException e) {
            evemen.Imprimir_serial_sql_error(e, sql, titulo);
        }
        return sumaventa;
    }

    public static String getEstado_ven_EMITIDO() {
        return estado_ven_EMITIDO;
    }

    public static String getEstado_ven_ANULADO() {
        return estado_ven_ANULADO;
    }

    public static String getEstado_ven_ANULADO_temp() {
        return estado_ven_ANULADO_temp;
    }

    public static String getEstado_ven_TERMINADO() {
        return estado_ven_TERMINADO;
    }
    
}
