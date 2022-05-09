	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.factura_autoimpresor;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_factura_autoimpresor {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "FACTURA_AUTOIMPRESOR GUARDADO CORRECTAMENTE";
	private String mensaje_update = "FACTURA_AUTOIMPRESOR MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO factura_autoimpresor(idfactura_autoimpresor,fecha_creado,creado_por,condicion,forma_pago,moneda_pago,estado,cod_establecimiento,punto_expedicion,numeracion_secuencial,total_pagar,total_exenta,total_gravada_5,total_gravada_10,liquidacion_iva_5,liquidacion_iva_10,cuenta_5,cuenta_10,cuenta_exenta,total_item,total_articulo,monto_letra,observacion,fk_idtimbrado,fk_idcliente,fk_idcajero,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private String sql_update = "UPDATE factura_autoimpresor SET fecha_creado=?,creado_por=?,condicion=?,forma_pago=?,moneda_pago=?,estado=?,cod_establecimiento=?,punto_expedicion=?,numeracion_secuencial=?,total_pagar=?,total_exenta=?,total_gravada_5=?,total_gravada_10=?,liquidacion_iva_5=?,liquidacion_iva_10=?,cuenta_5=?,cuenta_10=?,cuenta_exenta=?,total_item=?,total_articulo=?,monto_letra=?,observacion=?,fk_idtimbrado=?,fk_idcliente=?,fk_idcajero=?,fk_idusuario=? WHERE idfactura_autoimpresor=?;";
	private String sql_select = "SELECT idfactura_autoimpresor,fecha_creado,creado_por,condicion,forma_pago,moneda_pago,estado,cod_establecimiento,punto_expedicion,numeracion_secuencial,total_pagar,total_exenta,total_gravada_5,total_gravada_10,liquidacion_iva_5,liquidacion_iva_10,cuenta_5,cuenta_10,cuenta_exenta,total_item,total_articulo,monto_letra,observacion,fk_idtimbrado,fk_idcliente,fk_idcajero,fk_idusuario FROM factura_autoimpresor order by 1 desc;";
	private String sql_cargar = "SELECT idfactura_autoimpresor,fecha_creado,creado_por,condicion,forma_pago,moneda_pago,estado,cod_establecimiento,punto_expedicion,numeracion_secuencial,total_pagar,total_exenta,total_gravada_5,total_gravada_10,liquidacion_iva_5,liquidacion_iva_10,cuenta_5,cuenta_10,cuenta_exenta,total_item,total_articulo,monto_letra,observacion,fk_idtimbrado,fk_idcliente,fk_idcajero,fk_idusuario FROM factura_autoimpresor WHERE idfactura_autoimpresor=";
	public void insertar_factura_autoimpresor(Connection conn, factura_autoimpresor fauto){
		fauto.setC1idfactura_autoimpresor(eveconn.getInt_ultimoID_mas_uno(conn, fauto.getTb_factura_autoimpresor(), fauto.getId_idfactura_autoimpresor()));
		String titulo = "insertar_factura_autoimpresor";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,fauto.getC1idfactura_autoimpresor());
			pst.setTimestamp(2,evefec.getTimestamp_sistema());
			pst.setString(3,fauto.getC3creado_por());
			pst.setString(4,fauto.getC4condicion());
			pst.setString(5,fauto.getC5forma_pago());
			pst.setString(6,fauto.getC6moneda_pago());
			pst.setString(7,fauto.getC7estado());
			pst.setInt(8,fauto.getC8cod_establecimiento());
			pst.setInt(9,fauto.getC9punto_expedicion());
			pst.setInt(10,fauto.getC10numeracion_secuencial());
			pst.setDouble(11,fauto.getC11total_pagar());
			pst.setDouble(12,fauto.getC12total_exenta());
			pst.setDouble(13,fauto.getC13total_gravada_5());
			pst.setDouble(14,fauto.getC14total_gravada_10());
			pst.setDouble(15,fauto.getC15liquidacion_iva_5());
			pst.setDouble(16,fauto.getC16liquidacion_iva_10());
			pst.setString(17,fauto.getC17cuenta_5());
			pst.setString(18,fauto.getC18cuenta_10());
			pst.setString(19,fauto.getC19cuenta_exenta());
			pst.setInt(20,fauto.getC20total_item());
			pst.setInt(21,fauto.getC21total_articulo());
			pst.setString(22,fauto.getC22monto_letra());
			pst.setString(23,fauto.getC23observacion());
			pst.setInt(24,fauto.getC24fk_idtimbrado());
			pst.setInt(25,fauto.getC25fk_idcliente());
			pst.setInt(26,fauto.getC26fk_idcajero());
			pst.setInt(27,fauto.getC27fk_idusuario());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + fauto.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + fauto.toString(), titulo);
		}
	}
	public void update_factura_autoimpresor(Connection conn, factura_autoimpresor fauto){
		String titulo = "update_factura_autoimpresor";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setTimestamp(1,evefec.getTimestamp_sistema());
			pst.setString(2,fauto.getC3creado_por());
			pst.setString(3,fauto.getC4condicion());
			pst.setString(4,fauto.getC5forma_pago());
			pst.setString(5,fauto.getC6moneda_pago());
			pst.setString(6,fauto.getC7estado());
			pst.setInt(7,fauto.getC8cod_establecimiento());
			pst.setInt(8,fauto.getC9punto_expedicion());
			pst.setInt(9,fauto.getC10numeracion_secuencial());
			pst.setDouble(10,fauto.getC11total_pagar());
			pst.setDouble(11,fauto.getC12total_exenta());
			pst.setDouble(12,fauto.getC13total_gravada_5());
			pst.setDouble(13,fauto.getC14total_gravada_10());
			pst.setDouble(14,fauto.getC15liquidacion_iva_5());
			pst.setDouble(15,fauto.getC16liquidacion_iva_10());
			pst.setString(16,fauto.getC17cuenta_5());
			pst.setString(17,fauto.getC18cuenta_10());
			pst.setString(18,fauto.getC19cuenta_exenta());
			pst.setInt(19,fauto.getC20total_item());
			pst.setInt(20,fauto.getC21total_articulo());
			pst.setString(21,fauto.getC22monto_letra());
			pst.setString(22,fauto.getC23observacion());
			pst.setInt(23,fauto.getC24fk_idtimbrado());
			pst.setInt(24,fauto.getC25fk_idcliente());
			pst.setInt(25,fauto.getC26fk_idcajero());
			pst.setInt(26,fauto.getC27fk_idusuario());
			pst.setInt(27,fauto.getC1idfactura_autoimpresor());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + fauto.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + fauto.toString(), titulo);
		}
	}
	public void cargar_factura_autoimpresor(Connection conn, factura_autoimpresor fauto,int idfactura_autoimpresor){
		String titulo = "Cargar_factura_autoimpresor";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+idfactura_autoimpresor, titulo);
			if(rs.next()){
				fauto.setC1idfactura_autoimpresor(rs.getInt(1));
				fauto.setC2fecha_creado(rs.getString(2));
				fauto.setC3creado_por(rs.getString(3));
				fauto.setC4condicion(rs.getString(4));
				fauto.setC5forma_pago(rs.getString(5));
				fauto.setC6moneda_pago(rs.getString(6));
				fauto.setC7estado(rs.getString(7));
				fauto.setC8cod_establecimiento(rs.getInt(8));
				fauto.setC9punto_expedicion(rs.getInt(9));
				fauto.setC10numeracion_secuencial(rs.getInt(10));
				fauto.setC11total_pagar(rs.getDouble(11));
				fauto.setC12total_exenta(rs.getDouble(12));
				fauto.setC13total_gravada_5(rs.getDouble(13));
				fauto.setC14total_gravada_10(rs.getDouble(14));
				fauto.setC15liquidacion_iva_5(rs.getDouble(15));
				fauto.setC16liquidacion_iva_10(rs.getDouble(16));
				fauto.setC17cuenta_5(rs.getString(17));
				fauto.setC18cuenta_10(rs.getString(18));
				fauto.setC19cuenta_exenta(rs.getString(19));
				fauto.setC20total_item(rs.getInt(20));
				fauto.setC21total_articulo(rs.getInt(21));
				fauto.setC22monto_letra(rs.getString(22));
				fauto.setC23observacion(rs.getString(23));
				fauto.setC24fk_idtimbrado(rs.getInt(24));
				fauto.setC25fk_idcliente(rs.getInt(25));
				fauto.setC26fk_idcajero(rs.getInt(26));
				fauto.setC27fk_idusuario(rs.getInt(27));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + fauto.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + fauto.toString(), titulo);
		}
	}
	public void actualizar_tabla_factura_autoimpresor(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_factura_autoimpresor(tbltabla);
	}
	public void ancho_tabla_factura_autoimpresor(JTable tbltabla){
		int Ancho[]={3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
