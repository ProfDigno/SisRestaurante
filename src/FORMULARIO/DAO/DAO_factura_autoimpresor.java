package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.factura_autoimpresor;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_factura_autoimpresor {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "FACTURA_AUTOIMPRESOR GUARDADO CORRECTAMENTE";
    private String mensaje_update = "FACTURA_AUTOIMPRESOR MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO factura_autoimpresor(idfactura_autoimpresor,fecha_creado,creado_por,condicion,forma_pago,moneda_pago,estado,cod_establecimiento,punto_expedicion,numeracion_secuencial,total_pagar,total_exenta,total_gravada_5,total_gravada_10,liquidacion_iva_5,liquidacion_iva_10,cuenta_5,cuenta_10,cuenta_exenta,total_item,total_articulo,monto_letra,observacion,fk_idtimbrado,fk_idcliente,fk_idcajero,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE factura_autoimpresor SET fecha_creado=?,creado_por=?,condicion=?,forma_pago=?,moneda_pago=?,estado=?,cod_establecimiento=?,punto_expedicion=?,numeracion_secuencial=?,total_pagar=?,total_exenta=?,total_gravada_5=?,total_gravada_10=?,liquidacion_iva_5=?,liquidacion_iva_10=?,cuenta_5=?,cuenta_10=?,cuenta_exenta=?,total_item=?,total_articulo=?,monto_letra=?,observacion=?,fk_idtimbrado=?,fk_idcliente=?,fk_idcajero=?,fk_idusuario=? WHERE idfactura_autoimpresor=?;";
    private String sql_select = "SELECT fa.idfactura_autoimpresor as idf,fa.total_item as it, to_char(fa.fecha_creado,'dd-MM-yyyy HH24:MI') as fecha, \n"
            + "TRIM(TRIM(to_char(fa.cod_establecimiento,'009'))||'-'||TRIM(to_char(fa.punto_expedicion,'009'))||'-'||TRIM(to_char(fa.numeracion_secuencial,'0000009'))) as nro_factura,\n"
            + "cl.nombre as cliente,cl.ruc,ti.numero as timbrado,ti.fecha_fin as tim_vence,\n"
            + "to_char(fa.total_pagar,'999G999G999') as total_pagar, \n"
            + "to_char(fa.liquidacion_iva_10,'999G999D99') as liq_iva_10,\n"
            + "fa.estado\n"
            + "FROM factura_autoimpresor fa,cliente cl,timbrado ti\n"
            + "where fa.fk_idcliente=cl.idcliente\n"
            + "and fa.fk_idtimbrado=ti.idtimbrado\n"
            + "order by 1 desc;";
    private String sql_cargar = "SELECT idfactura_autoimpresor,fecha_creado,creado_por,condicion,forma_pago,moneda_pago,estado,cod_establecimiento,punto_expedicion,numeracion_secuencial,total_pagar,total_exenta,total_gravada_5,total_gravada_10,liquidacion_iva_5,liquidacion_iva_10,cuenta_5,cuenta_10,cuenta_exenta,total_item,total_articulo,monto_letra,observacion,fk_idtimbrado,fk_idcliente,fk_idcajero,fk_idusuario FROM factura_autoimpresor WHERE idfactura_autoimpresor=";

    public void insertar_factura_autoimpresor(Connection conn, factura_autoimpresor fauto) {
        fauto.setC1idfactura_autoimpresor(eveconn.getInt_ultimoID_mas_uno(conn, fauto.getTb_factura_autoimpresor(), fauto.getId_idfactura_autoimpresor()));
        String titulo = "insertar_factura_autoimpresor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, fauto.getC1idfactura_autoimpresor());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, fauto.getC3creado_por());
            pst.setString(4, fauto.getC4condicion());
            pst.setString(5, fauto.getC5forma_pago());
            pst.setString(6, fauto.getC6moneda_pago());
            pst.setString(7, fauto.getC7estado());
            pst.setInt(8, fauto.getC8cod_establecimiento());
            pst.setInt(9, fauto.getC9punto_expedicion());
            pst.setInt(10, fauto.getC10numeracion_secuencial());
            pst.setDouble(11, fauto.getC11total_pagar());
            pst.setDouble(12, fauto.getC12total_exenta());
            pst.setDouble(13, fauto.getC13total_gravada_5());
            pst.setDouble(14, fauto.getC14total_gravada_10());
            pst.setDouble(15, fauto.getC15liquidacion_iva_5());
            pst.setDouble(16, fauto.getC16liquidacion_iva_10());
            pst.setString(17, fauto.getC17cuenta_5());
            pst.setString(18, fauto.getC18cuenta_10());
            pst.setString(19, fauto.getC19cuenta_exenta());
            pst.setInt(20, fauto.getC20total_item());
            pst.setInt(21, fauto.getC21total_articulo());
            pst.setString(22, fauto.getC22monto_letra());
            pst.setString(23, fauto.getC23observacion());
            pst.setInt(24, fauto.getC24fk_idtimbrado());
            pst.setInt(25, fauto.getC25fk_idcliente());
            pst.setInt(26, fauto.getC26fk_idcajero());
            pst.setInt(27, fauto.getC27fk_idusuario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + fauto.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + fauto.toString(), titulo);
        }
    }

    public void update_factura_autoimpresor(Connection conn, factura_autoimpresor fauto) {
        String titulo = "update_factura_autoimpresor";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, fauto.getC3creado_por());
            pst.setString(3, fauto.getC4condicion());
            pst.setString(4, fauto.getC5forma_pago());
            pst.setString(5, fauto.getC6moneda_pago());
            pst.setString(6, fauto.getC7estado());
            pst.setInt(7, fauto.getC8cod_establecimiento());
            pst.setInt(8, fauto.getC9punto_expedicion());
            pst.setInt(9, fauto.getC10numeracion_secuencial());
            pst.setDouble(10, fauto.getC11total_pagar());
            pst.setDouble(11, fauto.getC12total_exenta());
            pst.setDouble(12, fauto.getC13total_gravada_5());
            pst.setDouble(13, fauto.getC14total_gravada_10());
            pst.setDouble(14, fauto.getC15liquidacion_iva_5());
            pst.setDouble(15, fauto.getC16liquidacion_iva_10());
            pst.setString(16, fauto.getC17cuenta_5());
            pst.setString(17, fauto.getC18cuenta_10());
            pst.setString(18, fauto.getC19cuenta_exenta());
            pst.setInt(19, fauto.getC20total_item());
            pst.setInt(20, fauto.getC21total_articulo());
            pst.setString(21, fauto.getC22monto_letra());
            pst.setString(22, fauto.getC23observacion());
            pst.setInt(23, fauto.getC24fk_idtimbrado());
            pst.setInt(24, fauto.getC25fk_idcliente());
            pst.setInt(25, fauto.getC26fk_idcajero());
            pst.setInt(26, fauto.getC27fk_idusuario());
            pst.setInt(27, fauto.getC1idfactura_autoimpresor());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + fauto.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + fauto.toString(), titulo);
        }
    }

    public void cargar_factura_autoimpresor(Connection conn, factura_autoimpresor fauto, int idfactura_autoimpresor) {
        String titulo = "Cargar_factura_autoimpresor";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idfactura_autoimpresor, titulo);
            if (rs.next()) {
                fauto.setC1idfactura_autoimpresor(rs.getInt(1));
                fauto.setC2fecha_creado(rs.getString(2));
                fauto.setC3creado_por(rs.getString(3));
                fauto.setC4condicion(rs.getString(4));
                fauto.setC5forma_pago(rs.getString(5));
                fauto.setC6moneda_pago(rs.getString(6));
                fauto.setC7estado(rs.getString(7));
                fauto.setC8cod_establecimiento(rs.getInt(8));
                fauto.setC9punto_expedicion(rs.getInt(9));
                fauto.setC10numeracion_secuencial(rs.getInt(10));
                fauto.setC11total_pagar(rs.getDouble(11));
                fauto.setC12total_exenta(rs.getDouble(12));
                fauto.setC13total_gravada_5(rs.getDouble(13));
                fauto.setC14total_gravada_10(rs.getDouble(14));
                fauto.setC15liquidacion_iva_5(rs.getDouble(15));
                fauto.setC16liquidacion_iva_10(rs.getDouble(16));
                fauto.setC17cuenta_5(rs.getString(17));
                fauto.setC18cuenta_10(rs.getString(18));
                fauto.setC19cuenta_exenta(rs.getString(19));
                fauto.setC20total_item(rs.getInt(20));
                fauto.setC21total_articulo(rs.getInt(21));
                fauto.setC22monto_letra(rs.getString(22));
                fauto.setC23observacion(rs.getString(23));
                fauto.setC24fk_idtimbrado(rs.getInt(24));
                fauto.setC25fk_idcliente(rs.getInt(25));
                fauto.setC26fk_idcajero(rs.getInt(26));
                fauto.setC27fk_idusuario(rs.getInt(27));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + fauto.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + fauto.toString(), titulo);
        }
    }

    public void actualizar_tabla_factura_autoimpresor(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_factura_autoimpresor(tbltabla);
    }

    public void ancho_tabla_factura_autoimpresor(JTable tbltabla) {
        int Ancho[] = {2,2, 13, 13, 20, 8, 8, 9, 8, 8, 8};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
        evejt.alinear_derecha_columna(tbltabla, 8);
        evejt.alinear_derecha_columna(tbltabla, 9);
    }

    public void imprimir_ticket_factura_auto(Connection conn,int idfactura_autoimpresor,int cantidad_fila ) {
        String sql = "select fa.idfactura_autoimpresor as idfac,e.razon_social as emp_razon,e.ruc as emp_ruc,e.direccion as emp_direc,e.celular as emp_cel,\n"
                + "t.numero as tim_nro,to_char(t.fecha_inicio,'dd/MM/yyyy') as tim_fec_ini,to_char(t.fecha_fin,'dd/MM/yyyy') as tim_fec_fin,\n"
                + "TRIM(TRIM(to_char(fa.cod_establecimiento,'009'))||'-'||TRIM(to_char(fa.punto_expedicion,'009'))||'-'||TRIM(to_char(fa.numeracion_secuencial,'0000009'))) as nro_factura,\n"
                + "cl.nombre as cli_nombre,cl.ruc as cli_ruc,cl.direccion as cli_direc,cl.telefono as cli_tel,\n"
                + "fa.condicion as fac_condi,to_char(fa.fecha_creado,'dd/MM/yyyy HH24:MI:ss') as fac_fecha,\n"
                + "fa.total_pagar as fac_tt_pagar,fa.monto_letra as fac_tt_pag_letra,fa.total_exenta as fac_tt_exe,fa.total_gravada_5 as fac_tt_g5,fa.total_gravada_10 as fac_tt_g10,\n"
                + "fa.liquidacion_iva_5 as fac_liq_iva5,fa.liquidacion_iva_10 as fac_liq_iva10,(fa.liquidacion_iva_5+fa.liquidacion_iva_10) as fac_tt_liq_iva,\n"
                + "fa.creado_por as fac_cajero,e.mensaje_final,\n"
                + "ifa.fk_idproducto as item_cod,ifa.cantidad as item_cant,ifa.descripcion as item_descrip,ifa.precio_venta as tim_preciov,(ifa.cantidad*ifa.precio_venta) as tim_total \n"
                + "from empresa e,item_timbrado it,timbrado t,factura_autoimpresor fa,cliente cl,item_factura_auto ifa  \n"
                + "where e.idempresa=it.fk_idempresa \n"
                + "and it.fk_idtimbrado=t.idtimbrado\n"
                + "and fa.fk_idtimbrado=t.idtimbrado \n"
                + "and fa.fk_idcliente=cl.idcliente  \n"
                + "and fa.idfactura_autoimpresor=ifa.fk_idfactura_autoimpresor \n"
                + "and fa.idfactura_autoimpresor="+idfactura_autoimpresor
                + " order by ifa.iditem_factura_auto asc;";
        String direccion="src/REPORTE/FACTURA/repFacturaTicket.jrxml";
        String titulo="FACTURA TICKET";
        int tamano_detalle=30;
        int suma_PageHeight=tamano_detalle*(cantidad_fila-1);
        rep.imprimirjasper_tamano(conn, sql, titulo, direccion, suma_PageHeight);
        
    }
}
