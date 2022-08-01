/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMPRESORA_POS;

import BASEDATO.EvenConexion;
//import Config_JSON.json_config;
import Config_JSON.json_array_imprimir_pos;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_empresa;
import FORMULARIO.ENTIDAD.empresa;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.print.PrintException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Digno
 */
public class PosImprimir_Vale {

    EvenConexion eveconn = new EvenConexion();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
//    private static json_config config=new json_config();
    private static json_array_imprimir_pos jsprint=new json_array_imprimir_pos();
    ClaImpresoraPos pos = new ClaImpresoraPos();
    private empresa ENTemp = new empresa();
    private DAO_empresa DAOemp = new DAO_empresa();
    private static String tk_idvale = "500";
    private static String tk_fecha_emision = "08-03-2019";
    private static String tk_descripcion = "la descripcion";
    private static String tk_cliente = "el tipo de vale";
    private static String tk_monto = "15.000";
    private static String tk_usuario = "digno";
    private static String tk_nombre_empresa;
    private static String tk_ruta_archivo = "ticket_vale.txt";
    private static FileInputStream inputStream = null;
    private static String nombre_ticket = "-VALE";
    private void cargar_datos_vale(Connection conn, int idvale) {
        String titulo = "cargar_datos_vale";
        String sql = "select v.idvale as idg,(cl.nombre) as cliente,\n"
                + "v.descripcion as des_vale,\n"
                + "to_char(v.fecha_emision,'yyyy-MM-dd HH:MI') as fecha, \n"
                + "v.estado as estado,v.monto_vale as monto,\n"
                + "TRIM(to_char(v.monto_vale,'999G999G999')) as monto_pos,\n"
                + "us.nombre as usuario \n"
                + "from vale v, cliente cl,usuario us   \n"
                + "where v.fk_idcliente=cl.idcliente  \n"
                + "and v.fk_idusuario=us.idusuario \n"
                + "and v.idvale=" + idvale;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                tk_idvale = rs.getString("idg");
                tk_fecha_emision = rs.getString("fecha");
                tk_descripcion = rs.getString("des_vale");
                tk_cliente = rs.getString("cliente");
                tk_monto = rs.getString("monto_pos");
                tk_usuario = rs.getString("usuario");
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private String cargar_datos_para_mensaje_textarea() {
        String mensaje_impresora = "";
        String saltolinea = "\n";
        String tabular = "\t";
        mensaje_impresora = mensaje_impresora + "=======" + tk_nombre_empresa + nombre_ticket+"========" + saltolinea;
        mensaje_impresora = mensaje_impresora + "CODIGO:" + tk_idvale + saltolinea;
        mensaje_impresora = mensaje_impresora + "FECHA: " + tk_fecha_emision + saltolinea;
        mensaje_impresora = mensaje_impresora + "USUARIO: " + tk_usuario + saltolinea;
        mensaje_impresora = mensaje_impresora + "==========================================" + saltolinea;
        mensaje_impresora = mensaje_impresora + "DESCRIPCION: " + tk_descripcion + saltolinea;
        mensaje_impresora = mensaje_impresora + "FUNCIO.: " + tk_cliente + saltolinea;
        mensaje_impresora = mensaje_impresora + "==========================================" + saltolinea;
        mensaje_impresora = mensaje_impresora + "TOTAL :" + tk_monto + saltolinea;
        return mensaje_impresora;
    }

    private static void crear_archivo_texto_impresion() throws PrintException, FileNotFoundException, IOException {
        int totalColumna = jsprint.getTotal_columna();
        PrinterMatrix printer = new PrinterMatrix();
        Extenso e = new Extenso();
        e.setNumber(101.85);
        //Definir el tamanho del papel 
        int tempfila = 0;
        int totalfila = jsprint.getTt_fila_val();
        printer.setOutSize(totalfila, totalColumna);
        printer.printTextWrap(1 + tempfila, 1, jsprint.getSep_inicio(), totalColumna, jsprint.getLinea_cabezera() + nombre_ticket+jsprint.getLinea_cabezera());
        printer.printTextWrap(2 + tempfila, 2, jsprint.getSep_inicio(), totalColumna, "CODIGO:" + tk_idvale);
        printer.printTextWrap(2 + tempfila, 2, jsprint.getSep_fecha(), totalColumna, "FEC: " + tk_fecha_emision);
        printer.printTextWrap(3 + tempfila, 3, jsprint.getSep_inicio(), totalColumna, "USUARIO: " + tk_usuario);
        printer.printTextWrap(4 + tempfila, 4, jsprint.getSep_inicio(), totalColumna,jsprint.getLinea_separador());
        printer.printTextWrap(5 + tempfila, 5, jsprint.getSep_inicio(), 200, "DESCRIPCION: " + tk_descripcion);
        printer.printTextWrap(6 + tempfila, 6, jsprint.getSep_inicio(), 100, "FUNCIO.: " + tk_cliente);
        printer.printTextWrap(7 + tempfila, 7, jsprint.getSep_inicio(), totalColumna,jsprint.getLinea_separador());
        printer.printTextWrap(8 + tempfila, 8, jsprint.getSep_inicio(), totalColumna, "TOTAL :");
        printer.printTextWrap(8 + tempfila, 8, jsprint.getSep_total_gral(), totalColumna, tk_monto);
        printer.toFile(tk_ruta_archivo);
        try {
            inputStream = new FileInputStream(tk_ruta_archivo);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }
        if (inputStream == null) {
            return;
        }

    }

    void crear_archivo_enviar_impresora() {
        String titulo = "crear_archivo_enviar_impresora";
        try {
            crear_archivo_texto_impresion();
            pos.setInputStream(inputStream);
            pos.imprimir_ticket_Pos();
        } catch (Exception e) {
            evemen.mensaje_error(e, titulo);
        }
    }

    private void crear_mensaje_textarea_y_confirmar() {
        JTextArea ta = new JTextArea(20, 30);
        ta.setText(cargar_datos_para_mensaje_textarea());
        System.out.println(cargar_datos_para_mensaje_textarea());
        Object[] opciones = {"IMPRIMIR", "CANCELAR"};
        int eleccion = JOptionPane.showOptionDialog(null, new JScrollPane(ta), tk_ruta_archivo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "IMPRIMIR");
        if (eleccion == JOptionPane.YES_OPTION) {
            crear_archivo_enviar_impresora();
        }
    }

    public void boton_imprimir_pos_VALE(Connection conn, int idvale) {
        cargar_datos_vale(conn, idvale);
        DAOemp.cargar_empresa(conn, ENTemp, 1);
        tk_nombre_empresa=ENTemp.getC4razon_social();
        crear_mensaje_textarea_y_confirmar();
    }
}
