package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_factura_auto;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.usuario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_factura_auto {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private usuario ENTusu = new usuario();
    private String mensaje_insert = "ITEM_FACTURA_AUTO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_FACTURA_AUTO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_factura_auto(iditem_factura_auto,fecha_creado,creado_por,descripcion,cantidad,precio_venta,precio_compra,iva,total_exenta,total_gravada_5,total_gravada_10,cuenta_exenta,cuenta_5,cuenta_10,fk_idfactura_autoimpresor,fk_idproducto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE item_factura_auto SET fecha_creado=?,creado_por=?,descripcion=?,cantidad=?,precio_venta=?,precio_compra=?,iva=?,total_exenta=?,total_gravada_5=?,total_gravada_10=?,cuenta_exenta=?,cuenta_5=?,cuenta_10=?,fk_idfactura_autoimpresor=?,fk_idproducto=? WHERE iditem_factura_auto=?;";
    private String sql_select = "SELECT iditem_factura_auto,fecha_creado,creado_por,descripcion,cantidad,precio_venta,precio_compra,iva,total_exenta,total_gravada_5,total_gravada_10,cuenta_exenta,cuenta_5,cuenta_10,fk_idfactura_autoimpresor,fk_idproducto FROM item_factura_auto order by 1 desc;";
    private String sql_cargar = "SELECT iditem_factura_auto,fecha_creado,creado_por,descripcion,cantidad,precio_venta,precio_compra,iva,total_exenta,total_gravada_5,total_gravada_10,cuenta_exenta,cuenta_5,cuenta_10,fk_idfactura_autoimpresor,fk_idproducto FROM item_factura_auto WHERE iditem_factura_auto=";

    public void insertar_item_factura_auto(Connection conn, item_factura_auto itau) {
        itau.setC1iditem_factura_auto(eveconn.getInt_ultimoID_mas_uno(conn, itau.getTb_item_factura_auto(), itau.getId_iditem_factura_auto()));
        String titulo = "insertar_item_factura_auto";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, itau.getC1iditem_factura_auto());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, itau.getC3creado_por());
            pst.setString(4, itau.getC4descripcion());
            pst.setInt(5, itau.getC5cantidad());
            pst.setDouble(6, itau.getC6precio_venta());
            pst.setDouble(7, itau.getC7precio_compra());
            pst.setInt(8, itau.getC8iva());
            pst.setDouble(9, itau.getC9total_exenta());
            pst.setDouble(10, itau.getC10total_gravada_5());
            pst.setDouble(11, itau.getC11total_gravada_10());
            pst.setString(12, itau.getC12cuenta_exenta());
            pst.setString(13, itau.getC13cuenta_5());
            pst.setString(14, itau.getC14cuenta_10());
            pst.setInt(15, itau.getC15fk_idfactura_autoimpresor());
            pst.setInt(16, itau.getC16fk_idproducto());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + itau.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + itau.toString(), titulo);
        }
    }

    public void update_item_factura_auto(Connection conn, item_factura_auto itau) {
        String titulo = "update_item_factura_auto";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, itau.getC3creado_por());
            pst.setString(3, itau.getC4descripcion());
            pst.setInt(4, itau.getC5cantidad());
            pst.setDouble(5, itau.getC6precio_venta());
            pst.setDouble(6, itau.getC7precio_compra());
            pst.setInt(7, itau.getC8iva());
            pst.setDouble(8, itau.getC9total_exenta());
            pst.setDouble(9, itau.getC10total_gravada_5());
            pst.setDouble(10, itau.getC11total_gravada_10());
            pst.setString(11, itau.getC12cuenta_exenta());
            pst.setString(12, itau.getC13cuenta_5());
            pst.setString(13, itau.getC14cuenta_10());
            pst.setInt(14, itau.getC15fk_idfactura_autoimpresor());
            pst.setInt(15, itau.getC16fk_idproducto());
            pst.setInt(16, itau.getC1iditem_factura_auto());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + itau.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + itau.toString(), titulo);
        }
    }

    public void cargar_item_factura_auto(Connection conn, item_factura_auto itau, int iditem_factura_auto) {
        String titulo = "Cargar_item_factura_auto";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + iditem_factura_auto, titulo);
            if (rs.next()) {
                itau.setC1iditem_factura_auto(rs.getInt(1));
                itau.setC2fecha_creado(rs.getString(2));
                itau.setC3creado_por(rs.getString(3));
                itau.setC4descripcion(rs.getString(4));
                itau.setC5cantidad(rs.getInt(5));
                itau.setC6precio_venta(rs.getDouble(6));
                itau.setC7precio_compra(rs.getDouble(7));
                itau.setC8iva(rs.getInt(8));
                itau.setC9total_exenta(rs.getDouble(9));
                itau.setC10total_gravada_5(rs.getDouble(10));
                itau.setC11total_gravada_10(rs.getDouble(11));
                itau.setC12cuenta_exenta(rs.getString(12));
                itau.setC13cuenta_5(rs.getString(13));
                itau.setC14cuenta_10(rs.getString(14));
                itau.setC15fk_idfactura_autoimpresor(rs.getInt(15));
                itau.setC16fk_idproducto(rs.getInt(16));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + itau.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + itau.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_factura_auto(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_factura_auto(tbltabla);
    }

    public void ancho_tabla_item_factura_auto(JTable tbltabla) {
        int Ancho[] = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void insertar_item_factura_auto_de_venta(Connection conn, int idventa) {
        String sql = "INSERT INTO item_factura_auto( fecha_creado, creado_por, descripcion, cantidad, \n"
                + "            precio_venta, precio_compra, iva, total_exenta, total_gravada_5, \n"
                + "            total_gravada_10, cuenta_exenta, cuenta_5, cuenta_10, fk_idfactura_autoimpresor, \n"
                + "            fk_idproducto)\n"
                + "SELECT CURRENT_TIMESTAMP as fecha_creado,('"+ENTusu.getGlobal_nombre()+"') as creado_por,\n"
                + "iv.descripcion, iv.cantidad,iv.precio_venta, iv.precio_compra,         \n"
                + "       case when iv.tipo='P' then p.iva\n"
                + "            when iv.tipo='N' then p.iva\n"
                + "            when iv.tipo='I' then 10\n"
                + "            when iv.tipo='D' then 10\n"
                + "            else 0 end as iva,\n"
                + " case when iv.tipo='P' and p.iva=0 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='N' and p.iva=0 then (iv.precio_venta*iv.cantidad) \n"
                + "      else 0 end as total_exenta,\n"
                + " case when iv.tipo='P' and p.iva=5 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='N' and p.iva=5 then (iv.precio_venta*iv.cantidad) \n"
                + "      else 0 end as total_gravada_5,\n"
                + " case when iv.tipo='P' and p.iva=10 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='N' and p.iva=10 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='I' then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='D' then (iv.precio_venta*iv.cantidad) \n"
                + "      else 0 end as total_gravada_10,\n"
                + "case when iv.tipo='P' and p.iva=0 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta) \n"
                + "      when iv.tipo='N' and p.iva=0 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)\n"
                + "      else '---' end as cuenta_exenta,\n"
                + " case when iv.tipo='P' and p.iva=5 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)\n"
                + "      when iv.tipo='N' and p.iva=5 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)\n"
                + "      else '---' end as cuenta_5,\n"
                + " case when iv.tipo='P' and p.iva=10 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)\n"
                + "      when iv.tipo='N' and p.iva=10 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)\n"
                + "      when iv.tipo='I' then 'CUNETA INGREDIENTE' \n"
                + "      when iv.tipo='D' then 'CUENTA DELIVERY'\n"
                + "      else '---' end as cuenta_10,\n"
                + "(select COALESCE(max(idfactura_autoimpresor),0) from factura_autoimpresor) as idfactura_autoimpresor,\n"
                + "case when iv.tipo='P' then p.idproducto\n"
                + "     when iv.tipo='N' then p.idproducto\n"
                + "     when iv.tipo='I' then 0\n"
                + "     when iv.tipo='D' then 0\n"
                + "     else 0 end as fk_idproducto\n"
                + "  FROM item_venta iv,producto p\n"
                + "where iv.fk_idproducto=p.idproducto\n"
                + "and iv.precio_venta>0 "
                + "and iv.fk_idventa="+idventa+"; ";
        eveconn.SQL_execute_libre(conn, sql);
    }
}
