	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.cajero;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_cajero {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "CAJERO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "CAJERO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO cajero(idcajero,fecha_creado,creado_por,nombre,cedula,direccion,telefono,activo) VALUES (?,?,?,?,?,?,?,?);";
	private String sql_update = "UPDATE cajero SET fecha_creado=?,creado_por=?,nombre=?,cedula=?,direccion=?,telefono=?,activo=? WHERE idcajero=?;";
	private String sql_select = "SELECT idcajero,fecha_creado,creado_por,nombre,cedula,direccion,telefono,activo FROM cajero order by 1 desc;";
	private String sql_cargar = "SELECT idcajero,fecha_creado,creado_por,nombre,cedula,direccion,telefono,activo FROM cajero WHERE idcajero=";
	public void insertar_cajero(Connection conn, cajero caje){
		caje.setC1idcajero(eveconn.getInt_ultimoID_mas_uno(conn, caje.getTb_cajero(), caje.getId_idcajero()));
		String titulo = "insertar_cajero";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,caje.getC1idcajero());
			pst.setTimestamp(2,evefec.getTimestamp_sistema());
			pst.setString(3,caje.getC3creado_por());
			pst.setString(4,caje.getC4nombre());
			pst.setString(5,caje.getC5cedula());
			pst.setString(6,caje.getC6direccion());
			pst.setString(7,caje.getC7telefono());
			pst.setBoolean(8,caje.getC8activo());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + caje.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + caje.toString(), titulo);
		}
	}
	public void update_cajero(Connection conn, cajero caje){
		String titulo = "update_cajero";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setTimestamp(1,evefec.getTimestamp_sistema());
			pst.setString(2,caje.getC3creado_por());
			pst.setString(3,caje.getC4nombre());
			pst.setString(4,caje.getC5cedula());
			pst.setString(5,caje.getC6direccion());
			pst.setString(6,caje.getC7telefono());
			pst.setBoolean(7,caje.getC8activo());
			pst.setInt(8,caje.getC1idcajero());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + caje.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + caje.toString(), titulo);
		}
	}
	public void cargar_cajero(Connection conn, cajero caje,int idcajero){
		String titulo = "Cargar_cajero";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+idcajero, titulo);
			if(rs.next()){
				caje.setC1idcajero(rs.getInt(1));
				caje.setC2fecha_creado(rs.getString(2));
				caje.setC3creado_por(rs.getString(3));
				caje.setC4nombre(rs.getString(4));
				caje.setC5cedula(rs.getString(5));
				caje.setC6direccion(rs.getString(6));
				caje.setC7telefono(rs.getString(7));
				caje.setC8activo(rs.getBoolean(8));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + caje.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + caje.toString(), titulo);
		}
	}
	public void actualizar_tabla_cajero(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_cajero(tbltabla);
	}
	public void ancho_tabla_cajero(JTable tbltabla){
		int Ancho[]={12,12,12,12,12,12,12,12};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
