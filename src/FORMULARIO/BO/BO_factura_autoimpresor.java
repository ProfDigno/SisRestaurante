package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_factura_autoimpresor;
import FORMULARIO.DAO.DAO_item_factura_auto;
import FORMULARIO.DAO.DAO_timbrado;
import FORMULARIO.ENTIDAD.factura_autoimpresor;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_factura_autoimpresor {

    private DAO_factura_autoimpresor DAOfau = new DAO_factura_autoimpresor();
    private DAO_item_factura_auto DAOita=new DAO_item_factura_auto();
    private DAO_timbrado DAOtim = new DAO_timbrado();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public boolean getBoo_insertar_factura_autoimpresor(factura_autoimpresor ENTfau,int idventa) {
        boolean guardado=false;
        String titulo = "getBoo_insertar_factura_autoimpresor";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            DAOfau.insertar_factura_autoimpresor(conn, ENTfau);
            DAOita.insertar_item_factura_auto_de_venta(conn, idventa);
            DAOtim.update_timbrado_actual(conn, ENTfau.getC24fk_idtimbrado());
            conn.commit();
            guardado=true;
        } catch (SQLException e) {
            evmen.mensaje_error(e, ENTfau.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ENTfau.toString(), titulo);
            }
            guardado=false;
        }
        return guardado;
    }

    public void update_factura_autoimpresor(factura_autoimpresor fauto, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR FACTURA_AUTOIMPRESOR", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_factura_autoimpresor";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                DAOfau.update_factura_autoimpresor(conn, fauto);
                DAOfau.actualizar_tabla_factura_autoimpresor(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, fauto.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, fauto.toString(), titulo);
                }
            }
        }
    }
}
