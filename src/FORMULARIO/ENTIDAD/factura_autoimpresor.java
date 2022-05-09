	package FORMULARIO.ENTIDAD;
public class factura_autoimpresor {

//---------------DECLARAR VARIABLES---------------
private int C1idfactura_autoimpresor;
private String C2fecha_creado;
private String C3creado_por;
private String C4condicion;
private String C5forma_pago;
private String C6moneda_pago;
private String C7estado;
private int C8cod_establecimiento;
private int C9punto_expedicion;
private int C10numeracion_secuencial;
private double C11total_pagar;
private double C12total_exenta;
private double C13total_gravada_5;
private double C14total_gravada_10;
private double C15liquidacion_iva_5;
private double C16liquidacion_iva_10;
private String C17cuenta_5;
private String C18cuenta_10;
private String C19cuenta_exenta;
private int C20total_item;
private int C21total_articulo;
private String C22monto_letra;
private String C23observacion;
private int C24fk_idtimbrado;
private int C25fk_idcliente;
private int C26fk_idcajero;
private int C27fk_idusuario;
private static String nom_tabla;
private static String nom_idtabla;
private static boolean factura_cargada; 
//---------------TABLA-ID---------------
	public factura_autoimpresor() {
		setTb_factura_autoimpresor("factura_autoimpresor");
		setId_idfactura_autoimpresor("idfactura_autoimpresor");
	}
	public static String getTb_factura_autoimpresor(){
		return nom_tabla;
	}
	public static void setTb_factura_autoimpresor(String nom_tabla){
		factura_autoimpresor.nom_tabla = nom_tabla;
	}
	public static String getId_idfactura_autoimpresor(){
		return nom_idtabla;
	}
	public static void setId_idfactura_autoimpresor(String nom_idtabla){
		factura_autoimpresor.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idfactura_autoimpresor(){
		return C1idfactura_autoimpresor;
	}
	public void setC1idfactura_autoimpresor(int C1idfactura_autoimpresor){
		this.C1idfactura_autoimpresor = C1idfactura_autoimpresor;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public String getC3creado_por(){
		return C3creado_por;
	}
	public void setC3creado_por(String C3creado_por){
		this.C3creado_por = C3creado_por;
	}
	public String getC4condicion(){
		return C4condicion;
	}
	public void setC4condicion(String C4condicion){
		this.C4condicion = C4condicion;
	}
	public String getC5forma_pago(){
		return C5forma_pago;
	}
	public void setC5forma_pago(String C5forma_pago){
		this.C5forma_pago = C5forma_pago;
	}
	public String getC6moneda_pago(){
		return C6moneda_pago;
	}
	public void setC6moneda_pago(String C6moneda_pago){
		this.C6moneda_pago = C6moneda_pago;
	}
	public String getC7estado(){
		return C7estado;
	}
	public void setC7estado(String C7estado){
		this.C7estado = C7estado;
	}
	public int getC8cod_establecimiento(){
		return C8cod_establecimiento;
	}
	public void setC8cod_establecimiento(int C8cod_establecimiento){
		this.C8cod_establecimiento = C8cod_establecimiento;
	}
	public int getC9punto_expedicion(){
		return C9punto_expedicion;
	}
	public void setC9punto_expedicion(int C9punto_expedicion){
		this.C9punto_expedicion = C9punto_expedicion;
	}
	public int getC10numeracion_secuencial(){
		return C10numeracion_secuencial;
	}
	public void setC10numeracion_secuencial(int C10numeracion_secuencial){
		this.C10numeracion_secuencial = C10numeracion_secuencial;
	}
	public double getC11total_pagar(){
		return C11total_pagar;
	}
	public void setC11total_pagar(double C11total_pagar){
		this.C11total_pagar = C11total_pagar;
	}
	public double getC12total_exenta(){
		return C12total_exenta;
	}
	public void setC12total_exenta(double C12total_exenta){
		this.C12total_exenta = C12total_exenta;
	}
	public double getC13total_gravada_5(){
		return C13total_gravada_5;
	}
	public void setC13total_gravada_5(double C13total_gravada_5){
		this.C13total_gravada_5 = C13total_gravada_5;
	}
	public double getC14total_gravada_10(){
		return C14total_gravada_10;
	}
	public void setC14total_gravada_10(double C14total_gravada_10){
		this.C14total_gravada_10 = C14total_gravada_10;
	}
	public double getC15liquidacion_iva_5(){
		return C15liquidacion_iva_5;
	}
	public void setC15liquidacion_iva_5(double C15liquidacion_iva_5){
		this.C15liquidacion_iva_5 = C15liquidacion_iva_5;
	}
	public double getC16liquidacion_iva_10(){
		return C16liquidacion_iva_10;
	}
	public void setC16liquidacion_iva_10(double C16liquidacion_iva_10){
		this.C16liquidacion_iva_10 = C16liquidacion_iva_10;
	}
	public String getC17cuenta_5(){
		return C17cuenta_5;
	}
	public void setC17cuenta_5(String C17cuenta_5){
		this.C17cuenta_5 = C17cuenta_5;
	}
	public String getC18cuenta_10(){
		return C18cuenta_10;
	}
	public void setC18cuenta_10(String C18cuenta_10){
		this.C18cuenta_10 = C18cuenta_10;
	}
	public String getC19cuenta_exenta(){
		return C19cuenta_exenta;
	}
	public void setC19cuenta_exenta(String C19cuenta_exenta){
		this.C19cuenta_exenta = C19cuenta_exenta;
	}
	public int getC20total_item(){
		return C20total_item;
	}
	public void setC20total_item(int C20total_item){
		this.C20total_item = C20total_item;
	}
	public int getC21total_articulo(){
		return C21total_articulo;
	}
	public void setC21total_articulo(int C21total_articulo){
		this.C21total_articulo = C21total_articulo;
	}
	public String getC22monto_letra(){
		return C22monto_letra;
	}
	public void setC22monto_letra(String C22monto_letra){
		this.C22monto_letra = C22monto_letra;
	}
	public String getC23observacion(){
		return C23observacion;
	}
	public void setC23observacion(String C23observacion){
		this.C23observacion = C23observacion;
	}
	public int getC24fk_idtimbrado(){
		return C24fk_idtimbrado;
	}
	public void setC24fk_idtimbrado(int C24fk_idtimbrado){
		this.C24fk_idtimbrado = C24fk_idtimbrado;
	}
	public int getC25fk_idcliente(){
		return C25fk_idcliente;
	}
	public void setC25fk_idcliente(int C25fk_idcliente){
		this.C25fk_idcliente = C25fk_idcliente;
	}
	public int getC26fk_idcajero(){
		return C26fk_idcajero;
	}
	public void setC26fk_idcajero(int C26fk_idcajero){
		this.C26fk_idcajero = C26fk_idcajero;
	}
	public int getC27fk_idusuario(){
		return C27fk_idusuario;
	}
	public void setC27fk_idusuario(int C27fk_idusuario){
		this.C27fk_idusuario = C27fk_idusuario;
	}

    public static boolean isFactura_cargada() {
        return factura_cargada;
    }

    public static void setFactura_cargada(boolean factura_cargada) {
        factura_autoimpresor.factura_cargada = factura_cargada;
    }
        
	public String toString() {
		return "factura_autoimpresor(" + ",idfactura_autoimpresor=" + C1idfactura_autoimpresor + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,condicion=" + C4condicion + " ,forma_pago=" + C5forma_pago + " ,moneda_pago=" + C6moneda_pago + " ,estado=" + C7estado + " ,cod_establecimiento=" + C8cod_establecimiento + " ,punto_expedicion=" + C9punto_expedicion + " ,numeracion_secuencial=" + C10numeracion_secuencial + " ,total_pagar=" + C11total_pagar + " ,total_exenta=" + C12total_exenta + " ,total_gravada_5=" + C13total_gravada_5 + " ,total_gravada_10=" + C14total_gravada_10 + " ,liquidacion_iva_5=" + C15liquidacion_iva_5 + " ,liquidacion_iva_10=" + C16liquidacion_iva_10 + " ,cuenta_5=" + C17cuenta_5 + " ,cuenta_10=" + C18cuenta_10 + " ,cuenta_exenta=" + C19cuenta_exenta + " ,total_item=" + C20total_item + " ,total_articulo=" + C21total_articulo + " ,monto_letra=" + C22monto_letra + " ,observacion=" + C23observacion + " ,fk_idtimbrado=" + C24fk_idtimbrado + " ,fk_idcliente=" + C25fk_idcliente + " ,fk_idcajero=" + C26fk_idcajero + " ,fk_idusuario=" + C27fk_idusuario + " )";
	}
}
