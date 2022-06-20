/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Config_JSON.json_config_json;
import Evento.Jframe.EvenJFRAME;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Digno
 */
public class FrmRestableserSistema extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmRestableserSistema
     */
    EvenJFRAME evetbl = new EvenJFRAME();
    Connection conn = ConnPostgres.getConnPosgres();
    EvenConexion eveconn = new EvenConexion();
    private json_config_json jsconjs = new json_config_json();

    private void abrir_formulario() {
        this.setTitle("RESTABLESER SISTEMA");
        evetbl.centrar_formulario(this);
    }

    private void ejecutar_truncate() {
        if (jsconjs.getboo_ejecutar_por_password()) {
            String sumaSQL = "";
            if (jCventa.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE venta;\n";
            }
            if (jCitem_venta.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_venta;\n";
            }
            if (jCventa_mesa.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE venta_mesa;\n";
            }
            if (jCitem_venta_mesa.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_venta_mesa;\n";
            }
            if (jCitem_venta_mesa_venta.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_venta_mesa_venta;\n";
            }
            if (jCfactura.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE factura;\n";
            }
            if (jCitem_factura.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_factura;\n";
            }
            if (jCfactura_autoimpresor.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE factura_autoimpresor;\n";
            }
            if (jCitem_factura_auto.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_factura_auto;\n";
            }
            if (jCtimbrado.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE timbrado;\n";
            }
            if (jCitem_timbrado.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_timbrado;\n";
            }
            if (jCgasto.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE gasto;\n";
            }
            if (jCtipo_gasto.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE gasto_tipo;\n";
            }
            if (jCproducto.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE producto;\n";
            }
            if (jCproducto_categoria.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE producto_categoria;\n";
            }
            if (jCproducto_grupo.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE producto_grupo;\n";
            }
            if (jCproducto_ingrediente.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE producto_ingrediente;\n";
            }
            if (jCproducto_unidad.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE producto_unidad;\n";
            }
            if (jCinsumo_producto.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE insumo_producto;\n";
            }
            if (jCinsumo_categoria.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE insumo_categoria;\n";
            }
            if (jCinsumo_unidad.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE insumo_unidad;\n";
            }
            if (jCinsumo_item_producto.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE insumo_item_producto;\n";
            }
            if (jCitem_producto_ingrediente.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_producto_ingrediente;\n";
            }
            if (jCitemven_insumo.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE itemven_insumo;\n";
            }
            if (jCitem_compra.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_compra;\n";
            }
            if (jCitem_compra_insumo.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_compra_insumo;\n";
            }
            if (jCproveedor.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE proveedor;\n";
            }
            if (jCcliente.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE cliente;\n";
            }
            if (jCzona_delivery.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE zona_delivery;\n";
            }
            if (jCcaja_cierre.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE caja_cierre;\n";
            }
            if (jCitem_caja_cierre.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE item_caja_cierre;\n";
            }
            if (jCcaja_detalle.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE caja_detalle;\n";
            }
            if (jCusuario.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE usuario;\n";
            }
            if (jCcajero.isSelected()) {
                sumaSQL = sumaSQL + "TRUNCATE cajero;\n";
            }
            
            
            eveconn.SQL_execute_libre(conn, sumaSQL);
            
            JOptionPane.showMessageDialog(null, sumaSQL);
        }
    }

    public FrmRestableserSistema() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jCventa = new javax.swing.JCheckBox();
        jCventa_mesa = new javax.swing.JCheckBox();
        jCitem_venta = new javax.swing.JCheckBox();
        jCitem_venta_mesa = new javax.swing.JCheckBox();
        jCitem_venta_mesa_venta = new javax.swing.JCheckBox();
        jCfactura = new javax.swing.JCheckBox();
        jCitem_factura = new javax.swing.JCheckBox();
        jCfactura_autoimpresor = new javax.swing.JCheckBox();
        jCitem_factura_auto = new javax.swing.JCheckBox();
        jCtimbrado = new javax.swing.JCheckBox();
        jCitem_timbrado = new javax.swing.JCheckBox();
        jCgasto = new javax.swing.JCheckBox();
        jCtipo_gasto = new javax.swing.JCheckBox();
        jCproducto = new javax.swing.JCheckBox();
        jCproducto_categoria = new javax.swing.JCheckBox();
        jCproducto_grupo = new javax.swing.JCheckBox();
        jCproducto_ingrediente = new javax.swing.JCheckBox();
        jCproducto_unidad = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jCitem_producto_ingrediente = new javax.swing.JCheckBox();
        jCitemven_insumo = new javax.swing.JCheckBox();
        jCproveedor = new javax.swing.JCheckBox();
        jCusuario = new javax.swing.JCheckBox();
        jCinsumo_producto = new javax.swing.JCheckBox();
        jCinsumo_categoria = new javax.swing.JCheckBox();
        jCinsumo_unidad = new javax.swing.JCheckBox();
        jCinsumo_item_producto = new javax.swing.JCheckBox();
        jCitem_compra = new javax.swing.JCheckBox();
        jCitem_compra_insumo = new javax.swing.JCheckBox();
        jCcliente = new javax.swing.JCheckBox();
        jCzona_delivery = new javax.swing.JCheckBox();
        jCcaja_cierre = new javax.swing.JCheckBox();
        jCitem_caja_cierre = new javax.swing.JCheckBox();
        jCcaja_detalle = new javax.swing.JCheckBox();
        jCcajero = new javax.swing.JCheckBox();
        btnejecutar_truncate = new javax.swing.JButton();

        setClosable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLAS"));

        jCventa.setText("VENTA");

        jCventa_mesa.setText("VENTA MESA");

        jCitem_venta.setText("ITEM VENTA");

        jCitem_venta_mesa.setText("ITEM VENTA MESA");

        jCitem_venta_mesa_venta.setText("ITEM VENTA MESA VENTA");

        jCfactura.setText("FACTURA");

        jCitem_factura.setText("ITEM FACTURA");

        jCfactura_autoimpresor.setText("FACTURA AUTOIMPRESOR");

        jCitem_factura_auto.setText("ITEM FACTURA AUTO");

        jCtimbrado.setText("TIMBRADO");

        jCitem_timbrado.setText("ITEM TIMBRADO");

        jCgasto.setText("GASTO");

        jCtipo_gasto.setText("TIPO GASTO");

        jCproducto.setText("PRODUCTO");

        jCproducto_categoria.setText("PRODUCTO CATEGORIA");

        jCproducto_grupo.setText("PRODUCTO GRUPO");

        jCproducto_ingrediente.setText("PRODUCTO INGREDIENTE");

        jCproducto_unidad.setText("PRODUCTO UNIDAD");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCtimbrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCventa)
                            .addComponent(jCventa_mesa)
                            .addComponent(jCitem_venta)
                            .addComponent(jCitem_venta_mesa)
                            .addComponent(jCitem_venta_mesa_venta)
                            .addComponent(jCfactura)
                            .addComponent(jCitem_factura)
                            .addComponent(jCfactura_autoimpresor)
                            .addComponent(jCitem_factura_auto)
                            .addComponent(jCitem_timbrado)
                            .addComponent(jCgasto)
                            .addComponent(jCtipo_gasto)
                            .addComponent(jCproducto)
                            .addComponent(jCproducto_categoria)
                            .addComponent(jCproducto_grupo)
                            .addComponent(jCproducto_ingrediente)
                            .addComponent(jCproducto_unidad))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jCventa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_venta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCventa_mesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_venta_mesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_venta_mesa_venta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCfactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_factura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCfactura_autoimpresor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_factura_auto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCtimbrado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_timbrado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCgasto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCtipo_gasto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproducto_categoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproducto_grupo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproducto_ingrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproducto_unidad)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLAS"));

        jCitem_producto_ingrediente.setText("ITEM PRODUCTO INGREDIENTE");

        jCitemven_insumo.setText("ITEMVEN INSUMO");

        jCproveedor.setText("PROVEEDOR");

        jCusuario.setText("USUARIO");

        jCinsumo_producto.setText("INSUMO PRODUCTO");

        jCinsumo_categoria.setText("INSUMO CATEGORIA");

        jCinsumo_unidad.setText("INSUMO UNIDAD");

        jCinsumo_item_producto.setText("INSUMO ITEM PRODUCTO");

        jCitem_compra.setText("ITEM COMPRA");

        jCitem_compra_insumo.setText("ITEM COMPRA INSUMO");

        jCcliente.setText("CLIENTE");

        jCzona_delivery.setText("ZONA DELIVERY");

        jCcaja_cierre.setText("CAJA CIERRE");

        jCitem_caja_cierre.setText("ITEM CAJA CIERRE");

        jCcaja_detalle.setText("CAJA DETALLE");

        jCcajero.setText("CAJERO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCitemven_insumo)
                    .addComponent(jCproveedor)
                    .addComponent(jCusuario)
                    .addComponent(jCinsumo_producto)
                    .addComponent(jCinsumo_categoria)
                    .addComponent(jCinsumo_unidad)
                    .addComponent(jCinsumo_item_producto)
                    .addComponent(jCitem_producto_ingrediente)
                    .addComponent(jCitem_compra)
                    .addComponent(jCitem_compra_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCcliente)
                    .addComponent(jCzona_delivery)
                    .addComponent(jCcaja_cierre)
                    .addComponent(jCitem_caja_cierre)
                    .addComponent(jCcaja_detalle)
                    .addComponent(jCcajero))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jCinsumo_producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCinsumo_categoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCinsumo_unidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCinsumo_item_producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_producto_ingrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitemven_insumo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_compra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_compra_insumo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCcliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCzona_delivery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCcaja_cierre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCitem_caja_cierre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCcaja_detalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCusuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCcajero)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnejecutar_truncate.setText("EJECUTAR TRUNCATE");
        btnejecutar_truncate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnejecutar_truncateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnejecutar_truncate, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnejecutar_truncate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TRUNCAR TABLA", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnejecutar_truncateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnejecutar_truncateActionPerformed
        // TODO add your handling code here:
        ejecutar_truncate();
    }//GEN-LAST:event_btnejecutar_truncateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnejecutar_truncate;
    private javax.swing.JCheckBox jCcaja_cierre;
    private javax.swing.JCheckBox jCcaja_detalle;
    private javax.swing.JCheckBox jCcajero;
    private javax.swing.JCheckBox jCcliente;
    private javax.swing.JCheckBox jCfactura;
    private javax.swing.JCheckBox jCfactura_autoimpresor;
    private javax.swing.JCheckBox jCgasto;
    private javax.swing.JCheckBox jCinsumo_categoria;
    private javax.swing.JCheckBox jCinsumo_item_producto;
    private javax.swing.JCheckBox jCinsumo_producto;
    private javax.swing.JCheckBox jCinsumo_unidad;
    private javax.swing.JCheckBox jCitem_caja_cierre;
    private javax.swing.JCheckBox jCitem_compra;
    private javax.swing.JCheckBox jCitem_compra_insumo;
    private javax.swing.JCheckBox jCitem_factura;
    private javax.swing.JCheckBox jCitem_factura_auto;
    private javax.swing.JCheckBox jCitem_producto_ingrediente;
    private javax.swing.JCheckBox jCitem_timbrado;
    private javax.swing.JCheckBox jCitem_venta;
    private javax.swing.JCheckBox jCitem_venta_mesa;
    private javax.swing.JCheckBox jCitem_venta_mesa_venta;
    private javax.swing.JCheckBox jCitemven_insumo;
    private javax.swing.JCheckBox jCproducto;
    private javax.swing.JCheckBox jCproducto_categoria;
    private javax.swing.JCheckBox jCproducto_grupo;
    private javax.swing.JCheckBox jCproducto_ingrediente;
    private javax.swing.JCheckBox jCproducto_unidad;
    private javax.swing.JCheckBox jCproveedor;
    private javax.swing.JCheckBox jCtimbrado;
    private javax.swing.JCheckBox jCtipo_gasto;
    private javax.swing.JCheckBox jCusuario;
    private javax.swing.JCheckBox jCventa;
    private javax.swing.JCheckBox jCventa_mesa;
    private javax.swing.JCheckBox jCzona_delivery;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
