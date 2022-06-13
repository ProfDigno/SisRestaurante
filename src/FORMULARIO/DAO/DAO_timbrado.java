package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.timbrado;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_timbrado {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIMBRADO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIMBRADO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO timbrado(idtimbrado,fecha_creado,creado_por,mac_pc,numero,fecha_inicio,fecha_fin,cod_establecimiento,punto_expedicion,numero_inicial,numero_final,numero_actual,activo,es_vencido,numero_limite,dias_limite,numero_caja) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE timbrado SET fecha_creado=?,creado_por=?,mac_pc=?,numero=?,fecha_inicio=?,fecha_fin=?,cod_establecimiento=?,punto_expedicion=?,numero_inicial=?,numero_final=?,numero_actual=?,activo=?,es_vencido=?,numero_limite=?,dias_limite=?,numero_caja=? WHERE idtimbrado=?;";
    private String sql_select = "SELECT idtimbrado,fecha_creado,creado_por,mac_pc,numero,fecha_inicio,fecha_fin,cod_establecimiento,punto_expedicion,numero_inicial,numero_final,numero_actual,activo,es_vencido,numero_limite,dias_limite,numero_caja FROM timbrado order by 1 desc;";
    private String sql_cargar = "SELECT idtimbrado,fecha_creado,creado_por,"
            + "mac_pc,numero,fecha_inicio,fecha_fin,"
            + "cod_establecimiento,punto_expedicion,numero_inicial,numero_final,numero_actual,"
            + "activo,es_vencido,numero_limite,dias_limite,numero_caja,"
            + "(fecha_fin-current_date) as dia_vence_resto "
            + "FROM timbrado WHERE idtimbrado=";

    public void insertar_timbrado(Connection conn, timbrado tim) {
        tim.setC1idtimbrado(eveconn.getInt_ultimoID_mas_uno(conn, tim.getTb_timbrado(), tim.getId_idtimbrado()));
        String titulo = "insertar_timbrado";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, tim.getC1idtimbrado());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, tim.getC3creado_por());
            pst.setString(4, tim.getC4mac_pc());
            pst.setInt(5, tim.getC5numero());
            pst.setDate(6, evefec.getDate_fecha_cargado(tim.getC6fecha_inicio()));
            pst.setDate(7, evefec.getDate_fecha_cargado(tim.getC7fecha_fin()));
            pst.setInt(8, tim.getC8cod_establecimiento());
            pst.setInt(9, tim.getC9punto_expedicion());
            pst.setInt(10, tim.getC10numero_inicial());
            pst.setInt(11, tim.getC11numero_final());
            pst.setInt(12, tim.getC12numero_actual());
            pst.setBoolean(13, tim.getC13activo());
            pst.setBoolean(14, tim.getC14es_vencido());
            pst.setInt(15, tim.getC15numero_limite());
            pst.setInt(16, tim.getC16dias_limite());
            pst.setInt(17, tim.getC17numero_caja());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + tim.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + tim.toString(), titulo);
        }
    }

    public void update_timbrado(Connection conn, timbrado tim) {
        String titulo = "update_timbrado";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, tim.getC3creado_por());
            pst.setString(3, tim.getC4mac_pc());
            pst.setInt(4, tim.getC5numero());
            pst.setDate(5, evefec.getDate_fecha_cargado(tim.getC6fecha_inicio()));
            pst.setDate(6, evefec.getDate_fecha_cargado(tim.getC7fecha_fin()));
            pst.setInt(7, tim.getC8cod_establecimiento());
            pst.setInt(8, tim.getC9punto_expedicion());
            pst.setInt(9, tim.getC10numero_inicial());
            pst.setInt(10, tim.getC11numero_final());
            pst.setInt(11, tim.getC12numero_actual());
            pst.setBoolean(12, tim.getC13activo());
            pst.setBoolean(13, tim.getC14es_vencido());
            pst.setInt(14, tim.getC15numero_limite());
            pst.setInt(15, tim.getC16dias_limite());
            pst.setInt(16, tim.getC17numero_caja());
            pst.setInt(17, tim.getC1idtimbrado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + tim.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + tim.toString(), titulo);
        }
    }

    public void cargar_timbrado(Connection conn, timbrado tim, int idtimbrado) {
        String titulo = "Cargar_timbrado";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idtimbrado, titulo);
            if (rs.next()) {
                tim.setC1idtimbrado(rs.getInt(1));
                tim.setC2fecha_creado(rs.getString(2));
                tim.setC3creado_por(rs.getString(3));
                tim.setC4mac_pc(rs.getString(4));
                tim.setC5numero(rs.getInt(5));
                tim.setC6fecha_inicio(rs.getString(6));
                tim.setC7fecha_fin(rs.getString(7));
                tim.setC8cod_establecimiento(rs.getInt(8));
                tim.setC9punto_expedicion(rs.getInt(9));
                tim.setC10numero_inicial(rs.getInt(10));
                tim.setC11numero_final(rs.getInt(11));
                tim.setC12numero_actual(rs.getInt(12));
                tim.setC13activo(rs.getBoolean(13));
                tim.setC14es_vencido(rs.getBoolean(14));
                tim.setC15numero_limite(rs.getInt(15));
                tim.setC16dias_limite(rs.getInt(16));
                tim.setC17numero_caja(rs.getInt(17));
                tim.setDia_vence_resto(rs.getInt(18));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + tim.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + tim.toString(), titulo);
        }
    }

    public void actualizar_tabla_timbrado(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_timbrado(tbltabla);
    }

    public void ancho_tabla_timbrado(JTable tbltabla) {
        int Ancho[] = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void update_timbrado_actual(Connection conn, int idtimbrado) {
        String titulo = "update_timbrado_actual";
        String sql = "UPDATE public.timbrado\n"
                + "   SET  numero_actual=(numero_actual+1), es_vencido=(fecha_fin<=CURRENT_DATE)\n"
                + " WHERE idtimbrado=?;";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idtimbrado);
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql, titulo);
//            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }
}
