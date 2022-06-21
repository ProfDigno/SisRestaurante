/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.DAO;

import BASEDATO.LOCAL.ConnPostgres;
import BASEDATO.EvenConexion;
//import BASEDATO.SERVIDOR.ConnPostgres_SER;
import CONFIGURACION.EvenDatosPc;
import Config_JSON.json_config;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Jtable.EvenRender;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.BO_venta;
import FORMULARIO.ENTIDAD.venta;
import static FORMULARIO.VISTA.FrmComandaCocina.panelcomanda;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Digno
 */
public class DAO_comanda {

    private int tiempo_nivel_1 = 10;
    private int tiempo_nivel_2 = 20;
    private int tiempo_nivel_3 = 30;
    private int tiempo_nivel_4 = 40;
    private int tiempo_nivel_5 = 50;
//    static int tiempo_terminado = JSconfig.getTiempo_max_emitido();
    private Color color_nivel_1 = new Color(223, 247, 103);
    private Color color_nivel_2= new Color(233, 105, 109);
    private Color color_nivel_3 = Color.GRAY;
    private Color color_nivel_4 = Color.GRAY;
    private Color color_nivel_5 = Color.GRAY;
    private Color color_nivel_6=  new Color(233, 48, 52);

    public void color_campo_amarillo(JTextField txttiempo_amarillo) {
        txttiempo_amarillo.setText(String.valueOf(tiempo_nivel_1));
        txttiempo_amarillo.setBackground(color_nivel_1);
    }

    public void color_campo_anaranjado(JTextField txttiempo_anaranjado) {
        txttiempo_anaranjado.setText(String.valueOf(tiempo_nivel_2));
        txttiempo_anaranjado.setBackground(color_nivel_2);
    }

    public void color_campo_azul(JTextField txttiempo_azul) {
        txttiempo_azul.setText(String.valueOf(tiempo_nivel_3));
        txttiempo_azul.setBackground(color_nivel_3);
    }

    public void color_campo_rosado(JTextField txttiempo_rosado) {
        txttiempo_rosado.setText(String.valueOf(tiempo_nivel_4));
        txttiempo_rosado.setBackground(color_nivel_4);
    }

    public void color_campo_gris(JTextField txttiempo_gris) {
        txttiempo_gris.setText(String.valueOf(tiempo_nivel_5));
        txttiempo_gris.setBackground(color_nivel_5);
    }

    /**
     * @return the color_panel_anulado
     */
    public static boolean isColor_panel_anulado() {
        return color_panel_anulado;
    }

    /**
     * @param aColor_panel_anulado the color_panel_anulado to set
     */
    public static void setColor_panel_anulado(boolean aColor_panel_anulado) {
        color_panel_anulado = aColor_panel_anulado;
    }
//    Connection conn = ConnPostgres.getConnPosgres();

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable evejt = new EvenJtable();
    EvenRender everende = new EvenRender();
    EvenFecha evefec = new EvenFecha();
    EvenDatosPc evepc = new EvenDatosPc();
    EvenConexion eveconn = new EvenConexion();
    venta ven = new venta();
    BO_venta vBO = new BO_venta();
    EvenJTextField evejtf = new EvenJTextField();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    json_config JSconfig=new json_config();
//    ConnPostgres_SER conPsSER = new ConnPostgres_SER();
    private List<JTextArea> textarea;
    private static boolean cargar_panel_comanda;
    private static boolean color_panel_anulado;

    public DAO_comanda() {
        textarea = new ArrayList<>();
    }

    private void limpiar_panel_area() {
        try {
            panelcomanda.removeAll();
            textarea.clear();
            panelcomanda.updateUI();
        } catch (Exception e) {
            evemen.mensaje_error(e, "limpiar_panel_area");
        }
    }

    private void actualizar_panel_area() {
        try {
            panelcomanda.updateUI();
        } catch (Exception e) {
            evemen.mensaje_error(e, "actualizar_panel_area");
        }
    }

    private void cargar_panel_area(String suma_comanda, int row, int tiempo_sql, boolean es_anulado) {
        JTextArea area_local = new JTextArea();
        area_local = new JTextArea(suma_comanda);
        panelcomanda.add(area_local);
        textarea.add(area_local);
        if (tiempo_sql < (tiempo_nivel_1 * 60)) {
            color_panel_area(row, area_local, Color.WHITE, Color.BLACK);
        }
        if (tiempo_sql > (tiempo_nivel_1 * 60) && tiempo_sql <= (tiempo_nivel_2 * 60)) {
            color_panel_area(row, area_local, color_nivel_1, Color.BLACK);
        }
        if (tiempo_sql > (tiempo_nivel_2 * 60) && tiempo_sql <= (tiempo_nivel_3 * 60)) {
            color_panel_area(row, area_local, color_nivel_3, Color.BLACK);
        }
        if (tiempo_sql > (tiempo_nivel_3 * 60) && tiempo_sql <= (tiempo_nivel_4 * 60)) {
            color_panel_area(row, area_local, color_nivel_3, Color.BLACK);
        }
        if (tiempo_sql > (tiempo_nivel_4 * 60) && tiempo_sql <= (tiempo_nivel_5 * 60)) {
            color_panel_area(row, area_local, color_nivel_4, Color.BLACK);
        }
        if (tiempo_sql >= (tiempo_nivel_5 * 60)) {
            color_panel_area(row, area_local, color_nivel_5, Color.WHITE);
        }
        if (es_anulado) {
            color_panel_area(row, area_local, color_nivel_6, Color.WHITE);
            setColor_panel_anulado(true);
        }
    }

    private void color_panel_area(int row, JTextArea area_local, Color fondo, Color texto) {
        textarea.set(row, area_local).setBackground(fondo);
        textarea.set(row, area_local).setForeground(texto);
    }

    private void anular_venta_anulado_temp(Connection conn) {
        String titulo = "anular_venta_anulado_temp";
        String sql_update = "update venta set estado='ANULADO' where estado='ANULADO_temp' ";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.execute();
            pst.close();
            setColor_panel_anulado(false);
            evemen.Imprimir_serial_sql(sql_update + "\n", titulo);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n", titulo);
        }
    }
    public void update_anular_venta_anulado_temp(Connection conn) {
        String titulo = "insertar_venta";
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            anular_venta_anulado_temp(conn);
            conn.commit();
        } catch (SQLException e) {
            evemen.mensaje_error(e, ven.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evemen.Imprimir_serial_sql_error(e1, ven.toString(), titulo);
            }
        }
    }
    public void cargar_sql_comanda(Connection conn) {
        if (isCargar_panel_comanda()) {
            limpiar_panel_area();
            String titulo = "cargar_sql_comanda";
            String fecha = evefec.getString_formato_fecha();
            String sql = "select v.idventa,(to_char(v.fecha_inicio,'HH24:MI')) as hora,v.tipo_entrega,v.comanda,v.estado, \n"
                    + "(to_char(v.fecha_inicio,'ssss')) as tiempo,"
                    + "(v.estado='ANULADO_temp') as anulado,v.indice,c.nombre as cliente,c.direccion \n "
                    + "from venta v,cliente c \n"
                    + "where v.fk_idcliente=c.idcliente \n"
                    + "and (date(v.fecha_inicio)=(current_date))\n"
                    + "and (v.estado='EMITIDO' or v.estado='ANULADO_temp' ) \n"
                    + "and v.comanda!='nada'\n"
                    + "order by v.idventa desc;";
            try {
                ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
                String linea = "\n***********************************\n";
                int row = 0;
                while (rs.next()) {
                    String estado = "";
                    int idventa = rs.getInt("idventa");
                    String hora = rs.getString("hora");
                    String tipo_entrega = rs.getString("tipo_entrega");
                    String comanda = rs.getString("comanda");
                    String indice = rs.getString("indice");
                    String cliente = rs.getString("cliente");
                    String direccion = rs.getString("direccion");
                    int tiempo_sql = rs.getInt("tiempo");
                    boolean es_anulado = rs.getBoolean("anulado");
                    int diferencia_tiempo = evefec.getInt_diferencia_en_segundo(tiempo_sql);
                    if (es_anulado) {
                        estado = "\n###ANULADO###\n###ANULADO###\n###ANULADO###";
                    }
                    String suma_comanda = "CLI="+cliente+"\n**IDV:(" + idventa + ")** HORA: " + hora + linea + tipo_entrega
                            + " R: " + evefec.getString_convertir_segundo_hora(diferencia_tiempo) + linea + comanda + estado;
                    cargar_panel_area(suma_comanda, row, diferencia_tiempo, es_anulado);
                    pasar_a_terminado(conn,diferencia_tiempo, idventa);
                    row++;
                }
                actualizar_panel_area();
            } catch (Exception e) {
                evemen.mensaje_error(e, sql, titulo);
            }
        }
    }

    private void pasar_a_terminado(Connection conn,int tiempo, int idventa) {
        if (tiempo >= (JSconfig.getTiempo_max_emitido() * 60)) {
            ven.setC5estado("TERMINADO");
            ven.setC1idventa(idventa);
            vBO.update_estado_venta(conn, ven);
        }
    }

    /**
     * @return the cargar_panel_comanda
     */
    public static boolean isCargar_panel_comanda() {
        return cargar_panel_comanda;
    }

    /**
     * @param aCargar_panel_comanda the cargar_panel_comanda to set
     */
    public static void setCargar_panel_comanda(boolean aCargar_panel_comanda) {
        cargar_panel_comanda = aCargar_panel_comanda;
    }
}
