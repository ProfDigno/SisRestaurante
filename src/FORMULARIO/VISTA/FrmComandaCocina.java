/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnPostgres;
import BASEDATO.EvenConexion;
//import BASEDATO.SERVIDOR.ConnPostgres_SER;
import CONFIGURACION.EvenDatosPc;
//import Config_JSON.json_config;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Jtable.EvenRender;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_comanda;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FrmComandaCocina extends javax.swing.JFrame {

    Connection conn = ConnPostgres.getConnPosgres();
//    ConnPostgres_SER conPsSER = new ConnPostgres_SER();
//    Connection connser = conPsSER.getConnPosgres();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable evejt = new EvenJtable();
    EvenRender everende = new EvenRender();
    EvenFecha evefec = new EvenFecha();
    EvenDatosPc evepc = new EvenDatosPc();
    EvenConexion eveconn = new EvenConexion();
    EvenJTextField evejtf = new EvenJTextField();
    control_vista covi=new control_vista();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    DAO_comanda coma = new DAO_comanda();
//     json_config JSconfig=new json_config();
    private List<JTextArea> textarea;
    private Timer tiempo;
    private int contador_tiempo = 0;
    private int contador_anular = 0;
    int maximo_bar=10;

    void abrir_formulario() {
        String servidor = "";
        this.setTitle("COMANDA "+servidor);
        jPbar_tiempo.setMinimum(0);
        jPbar_tiempo.setMaximum(maximo_bar);
        covi.setComanda_abierto(false);
        setDefaultCloseOperation(1);
        coma.setCargar_panel_comanda(true);
        cargar_comanda();
        coma.color_campo_amarillo(txttiempo_amarillo);
        coma.color_campo_anaranjado(txttiempo_anaranjado);
        coma.color_campo_azul(txttiempo_azul);
        coma.color_campo_rosado(txttiempo_rosado);
        coma.color_campo_gris(txttiempo_gris);
        lblmensaje.setText("LUEGO DE ("+coma.getTiempo_max_emitido()+") MINUTOS LOS EMITIDOS PASAN A TERMINADO");
        iniciarTiempo();
    }
    void cargar_comanda(){
            coma.cargar_sql_comanda(conn);
    }
    void anular_venta_temp(){
            coma.update_anular_venta_anulado_temp(conn);
    }
    

    void maximizar() {
        if (!this.isMaximumSizeSet()) {
            this.setExtendedState(MAXIMIZED_BOTH);
        }
    }

    void barra_de_tiempo(int valor) {
        if (valor <= maximo_bar) {
            jPbar_tiempo.setValue(valor);
        }
    }

    void color_panel() {
        if (coma.isColor_panel_anulado()) {
            contador_anular++;
            if (contador_tiempo % 2 == 0) {
                jPanelprincipal.setBackground(Color.red);
            } else {
                jPanelprincipal.setBackground(Color.yellow);
            }
            if (contador_anular >= 30) {

                anular_venta_temp();
                contador_anular = 0;
            }
        } else {
            jPanelprincipal.setBackground(Color.LIGHT_GRAY);
            contador_anular = 0;
        }
    }
void iniciarTiempo() {
        tiempo = new Timer();
        //le asignamos una tarea al timer
        tiempo.schedule(new FrmComandaCocina.clasetimer(), 0, 1000 * 1);
        System.out.println("Timer Iniciado en COMANDA");
    }

    void pararTiempo() {
        tiempo.cancel();
        System.out.println("Timer Parado en COMANDA");
    }
    class clasetimer extends TimerTask {

        public void run() {
            contador_tiempo++;
            txtfechahora.setText(evefec.getString_formato_hora());
            if (contador_tiempo >= maximo_bar) {
                maximizar();
                cargar_comanda();
                contador_tiempo = 0;
            }
            color_panel();
            barra_de_tiempo(contador_tiempo);
        }
    }

    public FrmComandaCocina() {
        initComponents();
        abrir_formulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelprincipal = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelcomanda = new javax.swing.JPanel();
        txtfechahora = new javax.swing.JTextField();
        jPbar_tiempo = new javax.swing.JProgressBar();
        txttiempo_amarillo = new javax.swing.JTextField();
        txttiempo_anaranjado = new javax.swing.JTextField();
        txttiempo_azul = new javax.swing.JTextField();
        txttiempo_rosado = new javax.swing.JTextField();
        txttiempo_gris = new javax.swing.JTextField();
        lblmensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelprincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelcomanda.setLayout(new java.awt.GridLayout(0, 5));
        jScrollPane4.setViewportView(panelcomanda);

        txtfechahora.setEditable(false);
        txtfechahora.setBackground(new java.awt.Color(255, 102, 51));
        txtfechahora.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtfechahora.setForeground(new java.awt.Color(255, 255, 51));
        txtfechahora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfechahora.setText("jTextField1");

        txttiempo_amarillo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttiempo_amarillo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttiempo_amarillo.setText("00");

        txttiempo_anaranjado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttiempo_anaranjado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttiempo_anaranjado.setText("00");

        txttiempo_azul.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttiempo_azul.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttiempo_azul.setText("00");

        txttiempo_rosado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttiempo_rosado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttiempo_rosado.setText("00");

        txttiempo_gris.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttiempo_gris.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttiempo_gris.setText("00");

        lblmensaje.setText("jLabel1");

        javax.swing.GroupLayout jPanelprincipalLayout = new javax.swing.GroupLayout(jPanelprincipal);
        jPanelprincipal.setLayout(jPanelprincipalLayout);
        jPanelprincipalLayout.setHorizontalGroup(
            jPanelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelprincipalLayout.createSequentialGroup()
                .addGroup(jPanelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelprincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE))
                    .addGroup(jPanelprincipalLayout.createSequentialGroup()
                        .addComponent(txtfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPbar_tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttiempo_amarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttiempo_anaranjado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttiempo_azul, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttiempo_rosado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttiempo_gris, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblmensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelprincipalLayout.setVerticalGroup(
            jPanelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelprincipalLayout.createSequentialGroup()
                .addGroup(jPanelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelprincipalLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txttiempo_amarillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttiempo_anaranjado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttiempo_azul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttiempo_rosado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttiempo_gris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblmensaje))
                            .addComponent(jPbar_tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelprincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        coma.setCargar_panel_comanda(false);
        covi.setComanda_abierto(true);
        pararTiempo();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
//        abrir_formulario();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmComandaCocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmComandaCocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmComandaCocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmComandaCocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmComandaCocina().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelprincipal;
    private javax.swing.JProgressBar jPbar_tiempo;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblmensaje;
    public static javax.swing.JPanel panelcomanda;
    private javax.swing.JTextField txtfechahora;
    private javax.swing.JTextField txttiempo_amarillo;
    private javax.swing.JTextField txttiempo_anaranjado;
    private javax.swing.JTextField txttiempo_azul;
    private javax.swing.JTextField txttiempo_gris;
    private javax.swing.JTextField txttiempo_rosado;
    // End of variables declaration//GEN-END:variables
}
