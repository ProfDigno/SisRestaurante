	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_item_timbrado;
	import FORMULARIO.ENTIDAD.item_timbrado;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_item_timbrado {
private DAO_item_timbrado itim_dao = new DAO_item_timbrado();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_item_timbrado(item_timbrado itim, JTable tbltabla) {
		String titulo = "insertar_item_timbrado";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			itim_dao.insertar_item_timbrado(conn, itim);
			itim_dao.actualizar_tabla_item_timbrado(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, itim.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, itim.toString(), titulo);
			}
		}
	}
	public void update_item_timbrado(item_timbrado itim, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_TIMBRADO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_item_timbrado";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				itim_dao.update_item_timbrado(conn, itim);
				itim_dao.actualizar_tabla_item_timbrado(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, itim.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, itim.toString(), titulo);
				}
			}
		}
	}
}
