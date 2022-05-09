package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.producto_cuenta;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_producto_cuenta {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "PRODUCTO_CUENTA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "PRODUCTO_CUENTA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO producto_cuenta(idproducto_cuenta,fecha_creado,creado_por,descripcion,iva,activo) VALUES (?,?,?,?,?,?);";
    private String sql_update = "UPDATE producto_cuenta SET fecha_creado=?,creado_por=?,descripcion=?,iva=?,activo=? WHERE idproducto_cuenta=?;";
    private String sql_select = "SELECT idproducto_cuenta,descripcion,iva,activo FROM producto_cuenta order by 1 desc;";
    private String sql_cargar = "SELECT idproducto_cuenta,fecha_creado,creado_por,descripcion,iva,activo FROM producto_cuenta WHERE idproducto_cuenta=";

    public void insertar_producto_cuenta(Connection conn, producto_cuenta pcue) {
        pcue.setC1idproducto_cuenta(eveconn.getInt_ultimoID_mas_uno(conn, pcue.getTb_producto_cuenta(), pcue.getId_idproducto_cuenta()));
        String titulo = "insertar_producto_cuenta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, pcue.getC1idproducto_cuenta());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, pcue.getC3creado_por());
            pst.setString(4, pcue.getC4descripcion());
            pst.setInt(5, pcue.getC5iva());
            pst.setBoolean(6, pcue.getC6activo());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + pcue.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + pcue.toString(), titulo);
        }
    }

    public void update_producto_cuenta(Connection conn, producto_cuenta pcue) {
        String titulo = "update_producto_cuenta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, pcue.getC3creado_por());
            pst.setString(3, pcue.getC4descripcion());
            pst.setInt(4, pcue.getC5iva());
            pst.setBoolean(5, pcue.getC6activo());
            pst.setInt(6, pcue.getC1idproducto_cuenta());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + pcue.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + pcue.toString(), titulo);
        }
    }

    public void cargar_producto_cuenta(Connection conn, producto_cuenta pcue, int idproducto_cuenta) {
        String titulo = "Cargar_producto_cuenta";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idproducto_cuenta, titulo);
            if (rs.next()) {
                pcue.setC1idproducto_cuenta(rs.getInt(1));
                pcue.setC2fecha_creado(rs.getString(2));
                pcue.setC3creado_por(rs.getString(3));
                pcue.setC4descripcion(rs.getString(4));
                pcue.setC5iva(rs.getInt(5));
                pcue.setC6activo(rs.getBoolean(6));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + pcue.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + pcue.toString(), titulo);
        }
    }

    public void actualizar_tabla_producto_cuenta(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_producto_cuenta(tbltabla);
    }

    public void ancho_tabla_producto_cuenta(JTable tbltabla) {
        int Ancho[] = {10,70, 10, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
