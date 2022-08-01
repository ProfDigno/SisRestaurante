/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnPostgres;
import ESTADOS.EvenEstado;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import FORMULARIO.ENTIDAD.venta;
import IMPRESORA_POS.PosImprimir_Venta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author Digno
 */
public class JDpago_combinado extends javax.swing.JDialog {

    venta ven = new venta();
//    PosImprimir_Venta posv = new PosImprimir_Venta();
    Connection connLocal = null;
    ConnPostgres cpt = new ConnPostgres();
    EvenJTextField evejtf = new EvenJTextField();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenEstado esta = new EvenEstado();
    private String forma_pago_COMBINADO = "COMBINADO";
    private String tabla_origen_COMBINADO = "VENTA_";
    private boolean carga_inicio = true;

    void abrir_formulario() {
        this.setTitle("PAGO COMBINADO");
        connLocal = cpt.getConnPosgres();
        evetbl.centrar_formulario_JDialog(this);
        jFtotal_pagar.setValue(ven.getMonto_ventaGlobal());
        lblformapago.setText("Fp:" + FrmVenta.ENTven.getFormaPagoGlobal());
//        txtpagado_efectivo.setText(evejtf.getString_format_nro_decimal(ven.getMonto_ventaGlobal()));
//        txtpagado_tarjeta.setText("0");
//        calcular_en_pago(txtpagado_efectivo, txtpagado_tarjeta);
//        txtpagado_efectivo.grabFocus();
        carga_inicio = true;
        calcular_en_pago();
    }

    private double getDouble_campo_cero(JFormattedTextField txtpagado, int formapago, boolean carga_inicio) {
        double monto = 0;
        if (carga_inicio) {
            if (formapago == FrmVenta.ENTven.getFormaPagoGlobal()) {
                monto = ven.getMonto_ventaGlobal();
                System.out.println("Forma Pago:"+getStringFormaPago());
                System.out.println("nro Forma Pago:"+FrmVenta.ENTven.getFormaPagoGlobal());
            } else {
            }
        } else {
            if (txtpagado.getText().trim().length() >= 0) {
                monto = Double.parseDouble(txtpagado.getText());
            } else {
            }
        }
        txtpagado.setValue(monto);
        return monto;
    }

    private String getStringFormaPago() {
        String formapago = "ninguno";
        if (FrmVenta.ENTven.getFormaPagoGlobal() == 1) {
            formapago = esta.getForPago_Efectivo();
        }
        if (FrmVenta.ENTven.getFormaPagoGlobal() == 2) {
            formapago = esta.getForPago_TarjetaDebito();
        }
        if (FrmVenta.ENTven.getFormaPagoGlobal() == 3) {
            formapago = esta.getForPago_gtigo();
        }
        if (FrmVenta.ENTven.getFormaPagoGlobal() == 4) {
            formapago = esta.getForPago_gpersonal();
        }
        if (FrmVenta.ENTven.getFormaPagoGlobal() == 5) {
            formapago = esta.getForPago_transferencia();
        }
        if (FrmVenta.ENTven.getFormaPagoGlobal() == 6) {
            formapago = esta.getForPago_pix();
        }
        return formapago;
    }

    private void calcular_en_pago() {
        double monto_venta_efectivo = getDouble_campo_cero(jFpagado_efectivo, 1, carga_inicio);
        double monto_venta_tarjeta = getDouble_campo_cero(jFpagado_tarjeta, 2, carga_inicio);
        double monto_venta_gtigo = getDouble_campo_cero(jFpagado_gtigo, 3, carga_inicio);
        double monto_venta_gpersonal = getDouble_campo_cero(jFpagado_gpersonal, 4, carga_inicio);
        double monto_venta_transferencia = getDouble_campo_cero(jFpagado_transferencia, 5, carga_inicio);
        double monto_venta_pix = getDouble_campo_cero(jFpagado_pix, 6, carga_inicio);
        FrmVenta.ENTven.setC17forma_pago(getStringFormaPago());
        FrmVenta.ENTven.setC6monto_venta_efectivo(monto_venta_efectivo);
        FrmVenta.ENTven.setC18monto_venta_tarjeta(monto_venta_tarjeta);
        FrmVenta.caja.setC4monto_venta_efectivo(monto_venta_efectivo);
        FrmVenta.caja.setC10tabla_origen(tabla_origen_COMBINADO+getStringFormaPago());
        FrmVenta.caja.setC18monto_venta_tarjeta(monto_venta_tarjeta);
        FrmVenta.caja.setC19monto_venta_gtigo(monto_venta_gtigo);
        FrmVenta.caja.setC20monto_venta_gpersonal(monto_venta_gpersonal);
        FrmVenta.caja.setC21monto_venta_transferencia(monto_venta_transferencia);
        FrmVenta.caja.setC22monto_venta_pix(monto_venta_pix);
    }

    private void boton_cerrar() {
        this.dispose();
    }

    public JDpago_combinado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jPanel1 = new javax.swing.JPanel();
        jFtotal_pagar = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        btncerrar = new javax.swing.JButton();
        btnpago_efectivo = new javax.swing.JButton();
        btnpago_tarjeta = new javax.swing.JButton();
        jFpagado_efectivo = new javax.swing.JFormattedTextField();
        jFpagado_tarjeta = new javax.swing.JFormattedTextField();
        jFpagado_gtigo = new javax.swing.JFormattedTextField();
        btnpago_gtigo = new javax.swing.JButton();
        jFpagado_gpersonal = new javax.swing.JFormattedTextField();
        btnpago_gpersonal = new javax.swing.JButton();
        jFpagado_transferencia = new javax.swing.JFormattedTextField();
        btnpago_transferencia = new javax.swing.JButton();
        jFpagado_pix = new javax.swing.JFormattedTextField();
        btnpago_pix = new javax.swing.JButton();
        lblformapago = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jFtotal_pagar.setEditable(false);
        jFtotal_pagar.setBackground(new java.awt.Color(255, 255, 204));
        jFtotal_pagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFtotal_pagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFtotal_pagar.setFont(new java.awt.Font("Stencil", 0, 48)); // NOI18N
        jFtotal_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFtotal_pagarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("TOTAL A PAGAR:");

        btncerrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btncerrar.setText("CERRAR");
        btncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarActionPerformed(evt);
            }
        });

        btnpago_efectivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/pago_efectivo_1.png"))); // NOI18N

        btnpago_tarjeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/pago_tarjeta.png"))); // NOI18N

        jFpagado_efectivo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jFpagado_efectivo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFpagado_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFpagado_efectivo.setToolTipText("");
        jFpagado_efectivo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jFpagado_tarjeta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jFpagado_tarjeta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFpagado_tarjeta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFpagado_tarjeta.setToolTipText("");
        jFpagado_tarjeta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jFpagado_gtigo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jFpagado_gtigo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFpagado_gtigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFpagado_gtigo.setToolTipText("");
        jFpagado_gtigo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnpago_gtigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/pago_tigomoney.png"))); // NOI18N

        jFpagado_gpersonal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jFpagado_gpersonal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFpagado_gpersonal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFpagado_gpersonal.setToolTipText("");
        jFpagado_gpersonal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnpago_gpersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/billetera_personal.png"))); // NOI18N

        jFpagado_transferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jFpagado_transferencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFpagado_transferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFpagado_transferencia.setToolTipText("");
        jFpagado_transferencia.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnpago_transferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/pago_banco_1.png"))); // NOI18N

        jFpagado_pix.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jFpagado_pix.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFpagado_pix.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFpagado_pix.setToolTipText("");
        jFpagado_pix.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnpago_pix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta/pago_pix_1.png"))); // NOI18N

        lblformapago.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btncerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                            .addComponent(lblformapago)
                            .addGap(11, 11, 11))
                        .addComponent(jFtotal_pagar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jFpagado_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnpago_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jFpagado_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpago_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jFpagado_gtigo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpago_gtigo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jFpagado_gpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpago_gpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jFpagado_transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpago_transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jFpagado_pix, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpago_pix, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblformapago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFtotal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnpago_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jFpagado_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFpagado_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpago_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFpagado_gtigo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpago_gtigo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFpagado_gpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpago_gpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFpagado_transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpago_transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFpagado_pix, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpago_pix, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFtotal_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFtotal_pagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFtotal_pagarActionPerformed

    private void btncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarActionPerformed
        // TODO add your handling code here:
        boton_cerrar();
    }//GEN-LAST:event_btncerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDpago_combinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDpago_combinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDpago_combinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDpago_combinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDpago_combinado dialog = new JDpago_combinado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncerrar;
    private javax.swing.JButton btnpago_efectivo;
    private javax.swing.JButton btnpago_gpersonal;
    private javax.swing.JButton btnpago_gtigo;
    private javax.swing.JButton btnpago_pix;
    private javax.swing.JButton btnpago_tarjeta;
    private javax.swing.JButton btnpago_transferencia;
    private javax.swing.JFormattedTextField jFpagado_efectivo;
    private javax.swing.JFormattedTextField jFpagado_gpersonal;
    private javax.swing.JFormattedTextField jFpagado_gtigo;
    private javax.swing.JFormattedTextField jFpagado_pix;
    private javax.swing.JFormattedTextField jFpagado_tarjeta;
    private javax.swing.JFormattedTextField jFpagado_transferencia;
    private javax.swing.JFormattedTextField jFtotal_pagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblformapago;
    // End of variables declaration//GEN-END:variables
}
