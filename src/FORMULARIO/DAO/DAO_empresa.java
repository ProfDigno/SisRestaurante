package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.empresa;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_empresa {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "EMPRESA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "EMPRESA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO empresa(idempresa,fecha_creado,creado_por,razon_social,ruc,dv,direccion,celular,activo,mensaje_final) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE empresa SET fecha_creado=?,creado_por=?,razon_social=?,ruc=?,dv=?,direccion=?,celular=?,activo=?,mensaje_final=? WHERE idempresa=?;";
    private String sql_select = "SELECT idempresa,fecha_creado,creado_por,razon_social,ruc,dv,direccion,celular,activo,mensaje_final FROM empresa order by 1 desc;";
    private String sql_cargar = "SELECT idempresa,fecha_creado,creado_por,razon_social,ruc,dv,direccion,celular,activo,mensaje_final FROM empresa WHERE idempresa=";

    public void insertar_empresa(Connection conn, empresa emp) {
        emp.setC1idempresa(eveconn.getInt_ultimoID_mas_uno(conn, emp.getTb_empresa(), emp.getId_idempresa()));
        String titulo = "insertar_empresa";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, emp.getC1idempresa());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, emp.getC3creado_por());
            pst.setString(4, emp.getC4razon_social());
            pst.setInt(5, emp.getC5ruc());
            pst.setInt(6, emp.getC6dv());
            pst.setString(7, emp.getC7direccion());
            pst.setString(8, emp.getC8celular());
            pst.setBoolean(9, emp.getC9activo());
            pst.setString(10, emp.getC10mensaje_final());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + emp.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + emp.toString(), titulo);
        }
    }

    public void update_empresa(Connection conn, empresa emp) {
        String titulo = "update_empresa";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, emp.getC3creado_por());
            pst.setString(3, emp.getC4razon_social());
            pst.setInt(4, emp.getC5ruc());
            pst.setInt(5, emp.getC6dv());
            pst.setString(6, emp.getC7direccion());
            pst.setString(7, emp.getC8celular());
            pst.setBoolean(8, emp.getC9activo());
            pst.setString(9, emp.getC10mensaje_final());
            pst.setInt(10, emp.getC1idempresa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + emp.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + emp.toString(), titulo);
        }
    }

    public void cargar_empresa(Connection conn, empresa emp, int idempresa) {
        String titulo = "Cargar_empresa";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idempresa, titulo);
            if (rs.next()) {
                emp.setC1idempresa(rs.getInt(1));
                emp.setC2fecha_creado(rs.getString(2));
                emp.setC3creado_por(rs.getString(3));
                emp.setC4razon_social(rs.getString(4));
                emp.setC5ruc(rs.getInt(5));
                emp.setC6dv(rs.getInt(6));
                emp.setC7direccion(rs.getString(7));
                emp.setC8celular(rs.getString(8));
                emp.setC9activo(rs.getBoolean(9));
                emp.setC10mensaje_final(rs.getString(10));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + emp.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + emp.toString(), titulo);
        }
    }

    public void actualizar_tabla_empresa(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_empresa(tbltabla);
    }

    public void ancho_tabla_empresa(JTable tbltabla) {
        int Ancho[] = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
