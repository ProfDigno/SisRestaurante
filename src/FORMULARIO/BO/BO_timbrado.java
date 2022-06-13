package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_timbrado;
import FORMULARIO.DAO.DAO_timbrado;
import FORMULARIO.ENTIDAD.item_timbrado;
import FORMULARIO.ENTIDAD.timbrado;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_timbrado {

    private DAO_timbrado tim_dao = new DAO_timbrado();
    private DAO_item_timbrado itim_dao = new DAO_item_timbrado();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_timbrado(timbrado tim, JTable tbltabla,item_timbrado ENTit) {
        String titulo = "insertar_timbrado";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            tim_dao.insertar_timbrado(conn, tim);
            itim_dao.insertar_item_timbrado(conn, ENTit);
            tim_dao.actualizar_tabla_timbrado(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, tim.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, tim.toString(), titulo);
            }
        }
    }

    public void update_timbrado(timbrado tim, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIMBRADO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_timbrado";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                tim_dao.update_timbrado(conn, tim);
                tim_dao.actualizar_tabla_timbrado(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, tim.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, tim.toString(), titulo);
                }
            }
        }
    }
}
