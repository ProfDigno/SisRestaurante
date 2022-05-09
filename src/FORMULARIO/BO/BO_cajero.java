	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_cajero;
	import FORMULARIO.ENTIDAD.cajero;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_cajero {
private DAO_cajero caje_dao = new DAO_cajero();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_cajero(cajero caje, JTable tbltabla) {
		String titulo = "insertar_cajero";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			caje_dao.insertar_cajero(conn, caje);
			caje_dao.actualizar_tabla_cajero(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, caje.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, caje.toString(), titulo);
			}
		}
	}
	public void update_cajero(cajero caje, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR CAJERO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_cajero";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				caje_dao.update_cajero(conn, caje);
				caje_dao.actualizar_tabla_cajero(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, caje.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, caje.toString(), titulo);
				}
			}
		}
	}
}
