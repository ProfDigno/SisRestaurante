	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_producto_cuenta;
	import FORMULARIO.ENTIDAD.producto_cuenta;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_producto_cuenta {
private DAO_producto_cuenta pcue_dao = new DAO_producto_cuenta();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_producto_cuenta(producto_cuenta pcue, JTable tbltabla) {
		String titulo = "insertar_producto_cuenta";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			pcue_dao.insertar_producto_cuenta(conn, pcue);
			pcue_dao.actualizar_tabla_producto_cuenta(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, pcue.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, pcue.toString(), titulo);
			}
		}
	}
	public void update_producto_cuenta(producto_cuenta pcue, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR PRODUCTO_CUENTA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_producto_cuenta";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				pcue_dao.update_producto_cuenta(conn, pcue);
				pcue_dao.actualizar_tabla_producto_cuenta(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, pcue.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, pcue.toString(), titulo);
				}
			}
		}
	}
}
