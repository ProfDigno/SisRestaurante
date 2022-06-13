/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnPostgres;
import BASEDATO.EvenConexion;
import CONFIGURACION.EvenDatosPc;
import ESTADOS.EvenEstado;
import Evento.Color.cla_color_pelete;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Jtable.EvenRender;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Utilitario.EvenNumero_a_Letra;
import Evento.Utilitario.EvenUtil;
import FORMULARIO.BO.BO_factura;
import FORMULARIO.BO.BO_factura_autoimpresor;
import FORMULARIO.BO.BO_timbrado;
import FORMULARIO.DAO.DAO_cliente;
import FORMULARIO.DAO.DAO_factura;
import FORMULARIO.DAO.DAO_factura_autoimpresor;
import FORMULARIO.DAO.DAO_item_factura_auto;
import FORMULARIO.DAO.DAO_timbrado;
import FORMULARIO.ENTIDAD.cliente;
import FORMULARIO.ENTIDAD.factura;
import FORMULARIO.ENTIDAD.factura_autoimpresor;
import FORMULARIO.ENTIDAD.item_factura;
import FORMULARIO.ENTIDAD.item_factura_auto;
import FORMULARIO.ENTIDAD.timbrado;
import FORMULARIO.ENTIDAD.usuario;
import FORMULARIO.ENTIDAD.venta;
import IMPRESORA_POS.PosImprimir_Venta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Formatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmFacturaAutoImpresor extends javax.swing.JInternalFrame {

    private EvenJFRAME evetbl = new EvenJFRAME();
    private EvenJtable evejt = new EvenJtable();
    private EvenRender everende = new EvenRender();
    private EvenFecha evefec = new EvenFecha();
    private EvenDatosPc evepc = new EvenDatosPc();
    private EvenUtil eveut = new EvenUtil();
    private EvenConexion eveconn = new EvenConexion();
    private EvenJTextField evejtf = new EvenJTextField();
    private EvenNumero_a_Letra nl = new EvenNumero_a_Letra();
    private EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    private EvenEstado est = new EvenEstado();
    private usuario ENTusu = new usuario();
    private venta ENTven = new venta();
    private cliente ENTcli = new cliente();
    private factura_autoimpresor ENTfau = new factura_autoimpresor();
    private item_factura_auto ENTifa = new item_factura_auto();
    private timbrado ENTtim = new timbrado();
    private DAO_timbrado DAOtim = new DAO_timbrado();
    private DAO_cliente DAOcli = new DAO_cliente();
    private DAO_factura_autoimpresor DAOfau = new DAO_factura_autoimpresor();
    private DAO_item_factura_auto DAOita = new DAO_item_factura_auto();
    private BO_factura_autoimpresor BOfau = new BO_factura_autoimpresor();
    private BO_timbrado BOtim = new BO_timbrado();
    private Connection conn = ConnPostgres.getConnPosgres();

    private DefaultTableModel model_itemf = new DefaultTableModel();
    private cla_color_pelete clacolor = new cla_color_pelete();
    private String Stotalmonto;

    private String Siva;

    private double Diva;
    private double Dtotalmonto;
    private String nombre_formulario = "FACTURA AUTOIMPRESOR";
    //--------factura autoimpresor--------
    private int idfactura_autoimpresor;
    private String fecha_creado;
    private String creado_por;
    private String condicion;
    private String forma_pago;
    private String moneda_pago;
    private String estado;
    private int cod_establecimiento;
    private int punto_expedicion;
    private int numeracion_secuencial;
    private double total_pagar;
    private double total_exenta;
    private double total_gravada_5;
    private double total_gravada_10;
    private double liquidacion_iva_5;
    private double liquidacion_iva_10;
    private String cuenta_5;
    private String cuenta_10;
    private String cuenta_exenta;
    private int total_item;
    private int total_articulo;
    private String monto_letra;
    private String observacion;
    private int fk_idtimbrado;
    private int fk_idcliente;
    private int fk_idcajero;
    private int fk_idusuario;
    private boolean es_timbrado_vencido;
    private int tim_numero_final;
    private int tim_numero_actual;
    private int tim_numero_limite;
    private int tim_dias_limite;
    private int tim_dia_vence_resto;

    /**
     * Creates new form FrmFactura
     */
    private void abrir_formulario() {
        this.setTitle(nombre_formulario);
        evetbl.centrar_formulario(this);

        reestableser_factura();
        crear_item_producto();
        color_formulario();

        if (ENTfau.isFactura_cargada()) {
            seleccionar_cargar_cliente(4);
            cargar_itemfactura_deventa();
            suma_iva_gravada_liquidacion();
        }
    }

    private void color_formulario() {
        panel_insert.setBackground(clacolor.getColor_insertar_primario());
        panel_factura.setBackground(clacolor.getColor_insertar_secundario());
        panel_tabla.setBackground(clacolor.getColor_insertar_secundario());
        panel_base.setBackground(clacolor.getColor_insertar_primario());
    }

    private void iniciar_variable_factura() {
        idfactura_autoimpresor = 0;
        fecha_creado = "now()";
        creado_por = ENTusu.getGlobal_nombre();
        condicion = est.getCond_Contado();
        forma_pago = est.getForPago_Efectivo();
        moneda_pago = est.getMone_PYG();
        estado = est.getEst_Emitido();
        cod_establecimiento = 1;
        punto_expedicion = 1;
        numeracion_secuencial = 0;
        total_pagar = 0;
        total_exenta = 0;
        total_gravada_5 = 0;
        total_gravada_10 = 0;
        liquidacion_iva_5 = 0;
        liquidacion_iva_10 = 0;
        cuenta_5 = "iva5";
        cuenta_10 = "iva10";
        cuenta_exenta = "exenta";
        total_item = 0;
        total_articulo = 0;
        monto_letra = "cero";
        observacion = "ninguna";
        fk_idtimbrado = 1;
        fk_idcliente = 0;
        fk_idcajero = ENTusu.getGlobal_idcajero();
        fk_idusuario = ENTusu.getGlobal_idusuario();
    }

    void crear_item_producto() {
        String dato[] = {"id", "descripcion", "precio", "Cant", "total"};
        evejt.crear_tabla_datos(tblitem_producto, model_itemf, dato);
    }

    void ancho_item_producto() {
        int Ancho[] = {10, 54, 15, 6, 15};
        evejt.setAnchoColumnaJtable(tblitem_producto, Ancho);
    }

    private void cambiar_ruc_cliente() {
        DAOcli.cargar_cliente(conn, ENTcli, fk_idcliente);
        String ruc_nuevo = JOptionPane.showInputDialog(this, "DESEA CAMBIAR EL RUC DEL CLIENTE:\n"
                + "CLIENTE: " + ENTcli.getC3nombre() + "\n"
                + "DIRECCION: " + ENTcli.getC4direccion() + "\n"
                + "RUC ACTUAL: " + ENTcli.getC6ruc() + "\n"
                + "CARGAR EL NUEVO RUC DEL CLIENTE");
        if (ruc_nuevo.equals("")) {
            JOptionPane.showMessageDialog(this, "SE DEBE CARGAR EL RUC PARA CAMBIAR");
        } else {
            ENTcli.setC6ruc(ruc_nuevo);
            DAOcli.update_cliente(conn, ENTcli, true);
            cargar_cliente(fk_idcliente);
        }
    }

    private void cambiar_nombre_cliente() {
        DAOcli.cargar_cliente(conn, ENTcli, fk_idcliente);
        String nombre_nuevo = JOptionPane.showInputDialog(this, "DESEA CAMBIAR EL NOMBRE DEL CLIENTE:\n"
                + "CLIENTE: " + ENTcli.getC3nombre() + "\n"
                + "DIRECCION: " + ENTcli.getC4direccion() + "\n"
                + "RUC ACTUAL: " + ENTcli.getC6ruc() + "\n"
                + "CARGAR EL NUEVO NOMBRE DEL CLIENTE");
        if (nombre_nuevo.equals("")) {
            JOptionPane.showMessageDialog(this, "SE DEBE CARGAR EL NOMBRE PARA CAMBIAR");
        } else {
            ENTcli.setC3nombre(nombre_nuevo);
            DAOcli.update_cliente(conn, ENTcli, true);
            cargar_cliente(fk_idcliente);
        }
    }

    private void cambiar_direccion_cliente() {
        DAOcli.cargar_cliente(conn, ENTcli, fk_idcliente);
        String direccion_nuevo = JOptionPane.showInputDialog(this, "DESEA CAMBIAR LA DIRECCION DEL CLIENTE:\n"
                + "CLIENTE: " + ENTcli.getC3nombre() + "\n"
                + "DIRECCION: " + ENTcli.getC4direccion() + "\n"
                + "RUC ACTUAL: " + ENTcli.getC6ruc() + "\n"
                + "CARGAR EL NUEVO DIRECCION DEL CLIENTE");
        if (direccion_nuevo.equals("")) {
            JOptionPane.showMessageDialog(this, "SE DEBE CARGAR LA DIRECCION PARA CAMBIAR");
        } else {
            ENTcli.setC4direccion(direccion_nuevo);
            DAOcli.update_cliente(conn, ENTcli, true);
            cargar_cliente(fk_idcliente);
        }
    }

    void seleccionar_cargar_cliente(int tipo) {

        if (tipo == 2) {
            fk_idcliente = (eveconn.getInt_ultimoID_mas_uno(conn, ENTcli.getTabla(), ENTcli.getIdtabla())) - 1;
        }
        if (tipo == 4) {
            fk_idcliente = ENTven.getC12fk_idcliente_estatico();
        }
        cargar_cliente(fk_idcliente);
    }

    private void cargar_cliente(int fk_idcliente) {
        System.out.println("idcliente:" + fk_idcliente);
        lblidcliente.setText("id:" + fk_idcliente);
        DAOcli.cargar_cliente(conn, ENTcli, fk_idcliente);
        txtbucarCliente_nombre.setText(ENTcli.getC3nombre());
        txtbucarCliente_telefono.setText(ENTcli.getC5telefono());
        txtbucarCliente_ruc.setText(ENTcli.getC6ruc());
        txtbucarCliente_direccion.setText(ENTcli.getC4direccion());
    }

    void limpiar_cliente() {
        lblidcliente.setText("id:0");
        fk_idcliente = 1;
        txtbucarCliente_nombre.setText(null);
        txtbucarCliente_telefono.setText(null);
        txtbucarCliente_ruc.setText(null);
        txtbucarCliente_direccion.setText(null);
        txtbucarCliente_telefono.grabFocus();
    }

    void cargar_itemfactura_deventa() {
        String titulo = "cargar_itemfactura_deventa";
        String sql = "SELECT iditem_venta, descripcion, precio_venta, precio_compra, cantidad, \n"
                + "       tipo, fk_idventa, fk_idproducto,(precio_venta*cantidad) as total\n"
                + "  FROM public.item_venta "
                + "where precio_venta>0 and fk_idventa=" + ENTven.getC1idventa_estatico();
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            while (rs.next()) {
                String idproducto = (rs.getString("fk_idproducto"));
                String nombre = (rs.getString("descripcion"));
                String cantidad = (rs.getString("cantidad"));
                String precio_venta = (rs.getString("precio_venta"));
                String total = (rs.getString("total"));
                String dato[] = {idproducto, nombre, precio_venta, cantidad, total};
                evejt.cargar_tabla_datos(tblitem_producto, model_itemf, dato);
            }
//            sumarTotalgral(tblitem_producto);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private void suma_iva_gravada_liquidacion() {
        String titulo = "suma_iva_gravada_liquidacion";
        String sql = "SELECT count(*) as total_item,\n"
                + " sum(iv.cantidad) as total_articulo,  \n"
                + " sum(iv.precio_venta*iv.cantidad) as total_pagar ,\n"
                + " sum(case when iv.tipo='P' and p.iva=0 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='N' and p.iva=0 then (iv.precio_venta*iv.cantidad) \n"
                + "      else 0 end) as total_exenta,\n"
                + " sum(case when iv.tipo='P' and p.iva=5 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='N' and p.iva=5 then (iv.precio_venta*iv.cantidad) \n"
                + "      else 0 end) as total_gravada_5,\n"
                + " sum(case when iv.tipo='P' and p.iva=10 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='N' and p.iva=10 then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='I' then (iv.precio_venta*iv.cantidad) \n"
                + "      when iv.tipo='D' then (iv.precio_venta*iv.cantidad) \n"
                + "      else 0 end) as total_gravada_10,\n"
                + " sum(case when iv.tipo='P' and p.iva=5 then ((iv.precio_venta*iv.cantidad)/21) \n"
                + "      when iv.tipo='N' and p.iva=5 then ((iv.precio_venta*iv.cantidad)/21) \n"
                + "      else 0 end) as liquidacion_iva_5,\n"
                + " sum(case when iv.tipo='P' and p.iva=10 then ((iv.precio_venta*iv.cantidad)/11) \n"
                + "      when iv.tipo='N' and p.iva=10 then ((iv.precio_venta*iv.cantidad)/11)\n"
                + "      when iv.tipo='I' then ((iv.precio_venta*iv.cantidad)/11) \n"
                + "      when iv.tipo='D' then ((iv.precio_venta*iv.cantidad)/11) \n"
                + "      else 0 end) as liquidacion_iva_10,\n"
                + " sum(case when iv.tipo='P' and p.iva=5 then ((iv.precio_venta*iv.cantidad)/21) \n"
                + "      when iv.tipo='N' and p.iva=5 then ((iv.precio_venta*iv.cantidad)/21) \n"
                + "      else 0 end)+\n"
                + " sum(case when iv.tipo='P' and p.iva=10 then ((iv.precio_venta*iv.cantidad)/11) \n"
                + "      when iv.tipo='N' and p.iva=10 then ((iv.precio_venta*iv.cantidad)/11)\n"
                + "      when iv.tipo='I' then ((iv.precio_venta*iv.cantidad)/11) \n"
                + "      when iv.tipo='D' then ((iv.precio_venta*iv.cantidad)/11) \n"
                + "      else 0 end) as suma_iva "
                + "  FROM item_venta iv,producto p\n"
                + "where iv.fk_idproducto=p.idproducto\n"
                + "and iv.precio_venta>0\n"
                + "and iv.fk_idventa=" + ENTven.getC1idventa_estatico();
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                total_item = (rs.getInt("total_item"));
                total_articulo = (rs.getInt("total_articulo"));
                total_pagar = (rs.getDouble("total_pagar"));
                total_exenta = (rs.getDouble("total_exenta"));
                total_gravada_5 = (rs.getDouble("total_gravada_5"));
                total_gravada_10 = (rs.getDouble("total_gravada_10"));
                liquidacion_iva_5 = (rs.getDouble("liquidacion_iva_5"));
                liquidacion_iva_10 = (rs.getDouble("liquidacion_iva_10"));
                double suma_iva = (rs.getDouble("suma_iva"));
                Stotalmonto = String.valueOf(total_pagar);
                monto_letra = nl.Convertir(Stotalmonto, true);
                txttotalletra.setText(monto_letra);
                jFtotal_pagar.setValue(total_pagar);
                jFtotal_exenta.setValue(total_exenta);
                jFliquidacion_iva_5.setValue(liquidacion_iva_5);
                jFliquidacion_iva_10.setValue(liquidacion_iva_10);
                jFtotal_liq_iva.setValue(suma_iva);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    void boton_eliminar_item() {
        if (!evejt.getBoolean_validar_select(tblitem_producto)) {
            if (evejt.getBoolean_Eliminar_Fila(tblitem_producto, model_itemf)) {
//                sumarTotalgral(tblitem_producto);
            }
        }
    }

    boolean validar_guardar_factura() {
        System.out.println("tim_numero_actual:" + tim_numero_actual);
        System.out.println("tim_numero_final:" + tim_numero_final);
        System.out.println("tim_numero_limite:" + tim_numero_limite);
        /**
         * tim_numero_actual:1008 tim_numero_final:5000 tim_numero_limite:20
         */
        if ((tim_numero_final-tim_numero_actual) <= tim_numero_limite) {
            JOptionPane.showMessageDialog(this, "SU NUMERO DE FACTURA YA ESTA LLEGANDO AL LIMITE SE DEBE SOLICITAR NUEVO TIMBRADO"
                    + "\nNUMERO RESTANTE:"+(tim_numero_final-tim_numero_actual),"LIMITE DE NUMERO",JOptionPane.WARNING_MESSAGE);
        }
        if(tim_dia_vence_resto<tim_dias_limite){
            JOptionPane.showMessageDialog(null, "SU DIA DE VENCIMIENTO DE TIMBRADO SE APROXIMA:\nFECHA:" + ENTtim.getC7fecha_fin() + "\n DEBE SOLICITAR NUEVO TIMBRADO"
            ,"VENCIMIENTO",JOptionPane.WARNING_MESSAGE);
        }
        if (evejtf.getBoo_JTextField_vacio(txtnro_factura, "CARGUE UN NUMERO DE FACTURA")) {
            return false;
        }
        if (es_timbrado_vencido) {
            panel_factura.setBackground(Color.RED);
            txttim_fec_fin.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(null, "SU TIMBRADO VENCIO EL:\nFECHA:" + ENTtim.getC7fecha_fin() + "\n DEBE SOLICITAR NUEVO TIMBRADO"
            ,"VENCIDO",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((tim_numero_final<=tim_numero_actual)) {
            JOptionPane.showMessageDialog(this, "SU NUMERO DE FACTURA YA ESTA AGOTADO  SE DEBE SOLICITAR NUEVO TIMBRADO"
                    + "\nNUMERO RESTANTE:"+(tim_numero_final-tim_numero_actual),"NUMERO AGOTADO",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void ultimo_idfactura() {
        idfactura_autoimpresor = eveconn.getInt_ultimoID_mas_uno(conn, ENTfau.getTb_factura_autoimpresor(), ENTfau.getId_idfactura_autoimpresor());
        Formatter cod_est = new Formatter();
        cod_est.format("%03d", cod_establecimiento);
        Formatter punto_exp = new Formatter();
        punto_exp.format("%03d", punto_expedicion);
        Formatter numeracion_sec = new Formatter();
        numeracion_sec.format("%07d", numeracion_secuencial);
        txtidfactura.setText(String.valueOf(idfactura_autoimpresor));
        txtnro_factura.setText(cod_est + "-" + punto_exp + "-" + numeracion_sec);
    }

    private void reestableser_factura() {
        iniciar_variable_factura();
        cargar_timbrado();
        ultimo_idfactura();
        limpiar_cliente();
        txtfecha.setText(evefec.getString_formato_fecha_hora());
        evejt.limpiar_tabla_datos(model_itemf);
        DAOfau.actualizar_tabla_factura_autoimpresor(conn, tblfactura);
        txttotalletra.setText("0");
        jFtotal_pagar.setValue(0);
        jFtotal_liq_iva.setValue(0);
    }

    private void dato_condicion() {
        if (jRcondContado.isSelected()) {
            condicion = est.getCond_Contado();
        }
        if (jRcondCredito.isSelected()) {
            condicion = est.getCond_Credito();
        }
    }

    private void cargar_datos_factura() {
        creado_por = ENTusu.getGlobal_nombre();
        dato_condicion();
        forma_pago = est.getForPago_Efectivo();
        moneda_pago = est.getMone_PYG();
        estado = est.getEst_Emitido();
        suma_iva_gravada_liquidacion();
        fk_idusuario = ENTusu.getGlobal_idusuario();
        ENTfau.setC1idfactura_autoimpresor(idfactura_autoimpresor);//ok
        ENTfau.setC2fecha_creado(fecha_creado);//ok
        ENTfau.setC3creado_por(creado_por);//ok
        ENTfau.setC4condicion(condicion);//ok
        ENTfau.setC5forma_pago(forma_pago);//ok
        ENTfau.setC6moneda_pago(moneda_pago);//ok
        ENTfau.setC7estado(estado);//ok
        ENTfau.setC8cod_establecimiento(cod_establecimiento);
        ENTfau.setC9punto_expedicion(punto_expedicion);
        ENTfau.setC10numeracion_secuencial(numeracion_secuencial);
        ENTfau.setC11total_pagar(total_pagar);//ok
        ENTfau.setC12total_exenta(total_exenta);//ok
        ENTfau.setC13total_gravada_5(total_gravada_5);//ok
        ENTfau.setC14total_gravada_10(total_gravada_10);//ok
        ENTfau.setC15liquidacion_iva_5(liquidacion_iva_5);//ok
        ENTfau.setC16liquidacion_iva_10(liquidacion_iva_10);//ok
        ENTfau.setC17cuenta_5(cuenta_5);
        ENTfau.setC18cuenta_10(cuenta_10);
        ENTfau.setC19cuenta_exenta(cuenta_exenta);
        ENTfau.setC20total_item(total_item);//ok
        ENTfau.setC21total_articulo(total_articulo);//ok
        ENTfau.setC22monto_letra(monto_letra);
        ENTfau.setC23observacion(observacion);
        ENTfau.setC24fk_idtimbrado(fk_idtimbrado);
        ENTfau.setC25fk_idcliente(fk_idcliente);
        ENTfau.setC26fk_idcajero(fk_idcajero);
        ENTfau.setC27fk_idusuario(fk_idusuario);//ok
    }

    void boton_guardar_factura() {
        if (validar_guardar_factura()) {
            cargar_datos_factura();
            if (BOfau.getBoo_insertar_factura_autoimpresor(ENTfau, ENTven.getC1idventa_estatico())) {
                DAOfau.imprimir_ticket_factura_auto(conn, idfactura_autoimpresor, total_item);
                reestableser_factura();
                
                this.dispose();
//                DAOfau.imprimir_factura(conn, idfactura_autoimpresor);
            }
        }
    }

    void boton_anular_factura() {
        if (!evejt.getBoolean_validar_select(tblfactura)) {
            if (evemen.MensajeGeneral_warning("ESTAS SEGURO DE ANULAR ESTA FACTURA", "ANULAR", "ACEPTAR", "CANCELAR")) {
                int idfactura = evejt.getInt_select_id(tblfactura);
                
//                ENTfau.setC1idfactura(idfactura);
//                ENTfau.setC4estado("ANULADO");
//                if (BOfau.getBoolean_update_anular_factura(ENTfau)) {
//                    DAOfau.actualizar_tabla_factura(conn, tblfactura, "");
//                }
            }
        }
    }

    void boton_imprimir_factura() {
        if (!evejt.getBoolean_validar_select(tblfactura)) {
            int idfactura = evejt.getInt_select_id(tblfactura);
            int total_item=evejt.getInt_select(tblfactura,1);
                DAOfau.imprimir_ticket_factura_auto(conn, idfactura, total_item);
//            DAOfau.imprimir_factura(conn, idfactura);
        }
    }

    void cargar_timbrado() {
        DAOtim.cargar_timbrado(conn, ENTtim, fk_idtimbrado);
        es_timbrado_vencido = ENTtim.getC14es_vencido();
        txttim_fec_inicio.setText(ENTtim.getC6fecha_inicio());
        txttim_fec_fin.setText(ENTtim.getC7fecha_fin());
        txttim_nro_timbrado.setText(String.valueOf(ENTtim.getC5numero()));
        txttim_nro_inicio.setText(String.valueOf(ENTtim.getC10numero_inicial()));
        txttim_nro_final.setText(String.valueOf(ENTtim.getC11numero_final()));
        
        cod_establecimiento = ENTtim.getC8cod_establecimiento();
        punto_expedicion = ENTtim.getC9punto_expedicion();
        numeracion_secuencial = ENTtim.getC12numero_actual() + 1;
        tim_numero_final = ENTtim.getC11numero_final();
        tim_numero_actual = ENTtim.getC12numero_actual();
        tim_numero_limite = ENTtim.getC15numero_limite();
        tim_dias_limite=ENTtim.getC16dias_limite();
        tim_dia_vence_resto=ENTtim.getDia_vence_resto();
        txttim_nro_disponible.setText(String.valueOf(tim_numero_final-tim_numero_actual));
    }

    public FrmFacturaAutoImpresor() {
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

        gru_cond = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panel_insert = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttotalletra = new javax.swing.JTextField();
        jFtotal_liq_iva = new javax.swing.JFormattedTextField();
        jFtotal_pagar = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtbucarCliente_ruc = new javax.swing.JTextField();
        txtbucarCliente_nombre = new javax.swing.JTextField();
        txtbucarCliente_direccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtbucarCliente_telefono = new javax.swing.JTextField();
        btnnuevo_cliente = new javax.swing.JButton();
        btncambiar_ruc = new javax.swing.JButton();
        btncambiar_nombre = new javax.swing.JButton();
        btncambiar_direccion = new javax.swing.JButton();
        lblidcliente = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_producto = new javax.swing.JTable();
        txtnro_factura = new javax.swing.JTextField();
        jRcondContado = new javax.swing.JRadioButton();
        jRcondCredito = new javax.swing.JRadioButton();
        txtidfactura = new javax.swing.JTextField();
        jFliquidacion_iva_5 = new javax.swing.JFormattedTextField();
        jFtotal_exenta = new javax.swing.JFormattedTextField();
        jFliquidacion_iva_10 = new javax.swing.JFormattedTextField();
        panel_factura = new javax.swing.JPanel();
        btnguardar_factura = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txttim_fec_inicio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttim_fec_fin = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txttim_nro_timbrado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttim_nro_inicio = new javax.swing.JTextField();
        txttim_nro_final = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txttim_nro_disponible = new javax.swing.JTextField();
        panel_base = new javax.swing.JPanel();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblfactura = new javax.swing.JTable();
        btnanular = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        panel_insert.setBackground(new java.awt.Color(153, 204, 255));
        panel_insert.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("TOTAL LETRA:");

        txttotalletra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jFtotal_liq_iva.setBorder(javax.swing.BorderFactory.createTitledBorder("Tt. LIQ IVA:"));
        jFtotal_liq_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFtotal_liq_iva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFtotal_pagar.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL PAGAR:"));
        jFtotal_pagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFtotal_pagar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("CLIENTE:"));

        jLabel3.setText("CLI:");

        jLabel5.setText("RUC:");

        txtbucarCliente_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbucarCliente_rucKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucarCliente_rucKeyReleased(evt);
            }
        });

        txtbucarCliente_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbucarCliente_nombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucarCliente_nombreKeyReleased(evt);
            }
        });

        txtbucarCliente_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucarCliente_direccionKeyReleased(evt);
            }
        });

        jLabel6.setText("DIREC:");

        jLabel4.setText("TEL.:");

        txtbucarCliente_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbucarCliente_telefonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucarCliente_telefonoKeyReleased(evt);
            }
        });

        btnnuevo_cliente.setText("NUEVO CLIENTE");
        btnnuevo_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo_clienteActionPerformed(evt);
            }
        });

        btncambiar_ruc.setText("CAMBIAR RUC");
        btncambiar_ruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiar_rucActionPerformed(evt);
            }
        });

        btncambiar_nombre.setText("CAMBIAR NOMBRE");
        btncambiar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiar_nombreActionPerformed(evt);
            }
        });

        btncambiar_direccion.setText("CAMBIAR DIRECCION");
        btncambiar_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiar_direccionActionPerformed(evt);
            }
        });

        lblidcliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblidcliente.setText("id");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnnuevo_cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncambiar_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncambiar_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncambiar_direccion))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbucarCliente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(txtbucarCliente_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblidcliente))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addComponent(txtbucarCliente_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(5, 5, 5)
                        .addComponent(txtbucarCliente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(txtbucarCliente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtbucarCliente_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtbucarCliente_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtbucarCliente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblidcliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo_cliente)
                    .addComponent(btncambiar_ruc)
                    .addComponent(btncambiar_nombre)
                    .addComponent(btncambiar_direccion)))
        );

        txtfecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtfecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha:"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM FACTURA:"));

        tblitem_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblitem_producto.setRowHeight(25);
        tblitem_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblitem_productoMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblitem_producto);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        );

        txtnro_factura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnro_factura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnro_factura.setBorder(javax.swing.BorderFactory.createTitledBorder("NUMERO FACTURA:"));

        gru_cond.add(jRcondContado);
        jRcondContado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRcondContado.setSelected(true);
        jRcondContado.setText("CONTADO");

        gru_cond.add(jRcondCredito);
        jRcondCredito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRcondCredito.setText("CREDITO");

        txtidfactura.setEditable(false);
        txtidfactura.setBackground(new java.awt.Color(204, 204, 255));
        txtidfactura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtidfactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidfactura.setBorder(javax.swing.BorderFactory.createTitledBorder("IDFAC"));

        jFliquidacion_iva_5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tt. LIQ. 5%"));
        jFliquidacion_iva_5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFliquidacion_iva_5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFtotal_exenta.setBorder(javax.swing.BorderFactory.createTitledBorder("Tt. EXENTA:"));
        jFtotal_exenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFtotal_exenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jFtotal_exenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFtotal_exentaActionPerformed(evt);
            }
        });

        jFliquidacion_iva_10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tt. LIQ. 10%"));
        jFliquidacion_iva_10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFliquidacion_iva_10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout panel_insertLayout = new javax.swing.GroupLayout(panel_insert);
        panel_insert.setLayout(panel_insertLayout);
        panel_insertLayout.setHorizontalGroup(
            panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertLayout.createSequentialGroup()
                        .addComponent(jFtotal_exenta, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFliquidacion_iva_5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFliquidacion_iva_10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFtotal_liq_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFtotal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_insertLayout.createSequentialGroup()
                        .addComponent(txtidfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnro_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRcondContado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRcondCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_insertLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalletra)))
                .addContainerGap())
        );
        panel_insertLayout.setVerticalGroup(
            panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertLayout.createSequentialGroup()
                .addGroup(panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtidfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnro_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_insertLayout.createSequentialGroup()
                        .addComponent(jRcondContado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRcondCredito)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttotalletra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFtotal_exenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFliquidacion_iva_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFliquidacion_iva_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFtotal_liq_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFtotal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panel_factura.setBackground(new java.awt.Color(102, 102, 255));
        panel_factura.setBorder(javax.swing.BorderFactory.createTitledBorder("TIMBRADO"));

        btnguardar_factura.setText("GUARDAR");
        btnguardar_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_facturaActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Inicio:");

        txttim_fec_inicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttim_fec_inicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setText("Fecha Fin:");

        txttim_fec_fin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttim_fec_fin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setText("TIMBRADO:");

        txttim_nro_timbrado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttim_nro_timbrado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setText("Nro. Inicio:");

        txttim_nro_inicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttim_nro_inicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txttim_nro_final.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttim_nro_final.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setText("Nro. Final:");

        jLabel11.setText("Nro. Disponible:");

        txttim_nro_disponible.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttim_nro_disponible.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panel_facturaLayout = new javax.swing.GroupLayout(panel_factura);
        panel_factura.setLayout(panel_facturaLayout);
        panel_facturaLayout.setHorizontalGroup(
            panel_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_facturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar_factura, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(txttim_fec_inicio)
                    .addComponent(txttim_fec_fin)
                    .addComponent(txttim_nro_timbrado)
                    .addComponent(txttim_nro_inicio)
                    .addComponent(txttim_nro_final)
                    .addComponent(txttim_nro_disponible)
                    .addGroup(panel_facturaLayout.createSequentialGroup()
                        .addGroup(panel_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_facturaLayout.setVerticalGroup(
            panel_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_facturaLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttim_fec_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttim_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttim_nro_timbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttim_nro_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttim_nro_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttim_nro_disponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnguardar_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("DATOS FACTURA", jPanel1);

        panel_tabla.setBackground(new java.awt.Color(153, 153, 255));
        panel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("FACTURA"));

        tblfactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblfactura);

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );

        btnanular.setText("ANULAR");
        btnanular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanularActionPerformed(evt);
            }
        });

        btnimprimir.setText("IMPRIMIR FACTURA");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_baseLayout = new javax.swing.GroupLayout(panel_base);
        panel_base.setLayout(panel_baseLayout);
        panel_baseLayout.setHorizontalGroup(
            panel_baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_baseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnanular, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimprimir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_baseLayout.setVerticalGroup(
            panel_baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_baseLayout.createSequentialGroup()
                .addComponent(panel_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnanular)
                    .addComponent(btnimprimir))
                .addGap(0, 145, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("FILTRO FACTURA", panel_base);

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

    private void txtbucarCliente_direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_direccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbucarCliente_direccionKeyReleased

    private void txtbucarCliente_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_nombreKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtbucarCliente_nombreKeyPressed

    private void txtbucarCliente_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_nombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbucarCliente_nombreKeyReleased

    private void txtbucarCliente_telefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_telefonoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtbucarCliente_telefonoKeyPressed

    private void txtbucarCliente_telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_telefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbucarCliente_telefonoKeyReleased

    private void txtbucarCliente_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_rucKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtbucarCliente_rucKeyPressed

    private void txtbucarCliente_rucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarCliente_rucKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbucarCliente_rucKeyReleased

    private void btnnuevo_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo_clienteActionPerformed
        // TODO add your handling code here:
//        evejt.mostrar_JTabbedPane(jTabbedPane_VENTA, 2);
        evetbl.abrir_TablaJinternal(new FrmCliente());
    }//GEN-LAST:event_btnnuevo_clienteActionPerformed

    private void tblitem_productoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitem_productoMouseReleased
        // TODO add your handling code here:

//        actualizar_tabla_ingrediente();
    }//GEN-LAST:event_tblitem_productoMouseReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ancho_item_producto();
        DAOfau.ancho_tabla_factura_autoimpresor(tblfactura);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnguardar_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_facturaActionPerformed
        // TODO add your handling code here:
        boton_guardar_factura();
    }//GEN-LAST:event_btnguardar_facturaActionPerformed

    private void btnanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanularActionPerformed
        // TODO add your handling code here:
        boton_anular_factura();
    }//GEN-LAST:event_btnanularActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
        boton_imprimir_factura();
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void btncambiar_rucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiar_rucActionPerformed
        // TODO add your handling code here:
        cambiar_ruc_cliente();
    }//GEN-LAST:event_btncambiar_rucActionPerformed

    private void btncambiar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiar_nombreActionPerformed
        // TODO add your handling code here:
        cambiar_nombre_cliente();
    }//GEN-LAST:event_btncambiar_nombreActionPerformed

    private void btncambiar_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiar_direccionActionPerformed
        // TODO add your handling code here:
        cambiar_direccion_cliente();
    }//GEN-LAST:event_btncambiar_direccionActionPerformed

    private void jFtotal_exentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFtotal_exentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFtotal_exentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular;
    private javax.swing.JButton btncambiar_direccion;
    private javax.swing.JButton btncambiar_nombre;
    private javax.swing.JButton btncambiar_ruc;
    private javax.swing.JButton btnguardar_factura;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnnuevo_cliente;
    private javax.swing.ButtonGroup gru_cond;
    private javax.swing.JFormattedTextField jFliquidacion_iva_10;
    private javax.swing.JFormattedTextField jFliquidacion_iva_5;
    private javax.swing.JFormattedTextField jFtotal_exenta;
    private javax.swing.JFormattedTextField jFtotal_liq_iva;
    private javax.swing.JFormattedTextField jFtotal_pagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRcondContado;
    private javax.swing.JRadioButton jRcondCredito;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblidcliente;
    private javax.swing.JPanel panel_base;
    private javax.swing.JPanel panel_factura;
    private javax.swing.JPanel panel_insert;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tblfactura;
    private javax.swing.JTable tblitem_producto;
    private javax.swing.JTextField txtbucarCliente_direccion;
    private javax.swing.JTextField txtbucarCliente_nombre;
    private javax.swing.JTextField txtbucarCliente_ruc;
    private javax.swing.JTextField txtbucarCliente_telefono;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtidfactura;
    private javax.swing.JTextField txtnro_factura;
    private javax.swing.JTextField txttim_fec_fin;
    private javax.swing.JTextField txttim_fec_inicio;
    private javax.swing.JTextField txttim_nro_disponible;
    private javax.swing.JTextField txttim_nro_final;
    private javax.swing.JTextField txttim_nro_inicio;
    private javax.swing.JTextField txttim_nro_timbrado;
    private javax.swing.JTextField txttotalletra;
    // End of variables declaration//GEN-END:variables
}
