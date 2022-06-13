package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_timbrado;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_timbrado {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_TIMBRADO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_TIMBRADO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_timbrado(iditem_timbrado,fecha_creado,creado_por,fk_idempresa,fk_idtimbrado) VALUES (?,?,?,?,?);";
    private String sql_update = "UPDATE item_timbrado SET fecha_creado=?,creado_por=?,fk_idempresa=?,fk_idtimbrado=? WHERE iditem_timbrado=?;";
    private String sql_select = "SELECT iditem_timbrado,fecha_creado,creado_por,fk_idempresa,fk_idtimbrado FROM item_timbrado order by 1 desc;";
    private String sql_cargar = "SELECT iditem_timbrado,fecha_creado,creado_por,fk_idempresa,fk_idtimbrado FROM item_timbrado WHERE iditem_timbrado=";

    public void insertar_item_timbrado(Connection conn, item_timbrado itim) {
        itim.setC1iditem_timbrado(eveconn.getInt_ultimoID_mas_uno(conn, itim.getTb_item_timbrado(), itim.getId_iditem_timbrado()));
        String titulo = "insertar_item_timbrado";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, itim.getC1iditem_timbrado());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, itim.getC3creado_por());
            pst.setInt(4, itim.getC4fk_idempresa());
            pst.setInt(5, itim.getC5fk_idtimbrado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + itim.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + itim.toString(), titulo);
        }
    }

    public void update_item_timbrado(Connection conn, item_timbrado itim) {
        String titulo = "update_item_timbrado";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, itim.getC3creado_por());
            pst.setInt(3, itim.getC4fk_idempresa());
            pst.setInt(4, itim.getC5fk_idtimbrado());
            pst.setInt(5, itim.getC1iditem_timbrado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + itim.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + itim.toString(), titulo);
        }
    }

    public void cargar_item_timbrado(Connection conn, item_timbrado itim, int iditem_timbrado) {
        String titulo = "Cargar_item_timbrado";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + iditem_timbrado, titulo);
            if (rs.next()) {
                itim.setC1iditem_timbrado(rs.getInt(1));
                itim.setC2fecha_creado(rs.getString(2));
                itim.setC3creado_por(rs.getString(3));
                itim.setC4fk_idempresa(rs.getInt(4));
                itim.setC5fk_idtimbrado(rs.getInt(5));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + itim.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + itim.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_timbrado(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_timbrado(tbltabla);
    }

    public void ancho_tabla_item_timbrado(JTable tbltabla) {
        int Ancho[] = {20, 20, 20, 20, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
