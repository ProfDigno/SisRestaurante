	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_item_factura_auto;
	import FORMULARIO.ENTIDAD.item_factura_auto;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_item_factura_auto {
private DAO_item_factura_auto itau_dao = new DAO_item_factura_auto();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_item_factura_auto(item_factura_auto itau, JTable tbltabla) {
		String titulo = "insertar_item_factura_auto";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			itau_dao.insertar_item_factura_auto(conn, itau);
			itau_dao.actualizar_tabla_item_factura_auto(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, itau.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, itau.toString(), titulo);
			}
		}
	}
	public void update_item_factura_auto(item_factura_auto itau, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_FACTURA_AUTO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_item_factura_auto";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				itau_dao.update_item_factura_auto(conn, itau);
				itau_dao.actualizar_tabla_item_factura_auto(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, itau.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, itau.toString(), titulo);
				}
			}
		}
	}
}
