	package FORMULARIO.ENTIDAD;
public class item_factura_auto {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_factura_auto;
private String C2fecha_creado;
private String C3creado_por;
private String C4descripcion;
private int C5cantidad;
private double C6precio_venta;
private double C7precio_compra;
private int C8iva;
private double C9total_exenta;
private double C10total_gravada_5;
private double C11total_gravada_10;
private String C12cuenta_exenta;
private String C13cuenta_5;
private String C14cuenta_10;
private int C15fk_idfactura_autoimpresor;
private int C16fk_idproducto;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_factura_auto() {
		setTb_item_factura_auto("item_factura_auto");
		setId_iditem_factura_auto("iditem_factura_auto");
	}
	public static String getTb_item_factura_auto(){
		return nom_tabla;
	}
	public static void setTb_item_factura_auto(String nom_tabla){
		item_factura_auto.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_factura_auto(){
		return nom_idtabla;
	}
	public static void setId_iditem_factura_auto(String nom_idtabla){
		item_factura_auto.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_factura_auto(){
		return C1iditem_factura_auto;
	}
	public void setC1iditem_factura_auto(int C1iditem_factura_auto){
		this.C1iditem_factura_auto = C1iditem_factura_auto;
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
	public String getC4descripcion(){
		return C4descripcion;
	}
	public void setC4descripcion(String C4descripcion){
		this.C4descripcion = C4descripcion;
	}
	public int getC5cantidad(){
		return C5cantidad;
	}
	public void setC5cantidad(int C5cantidad){
		this.C5cantidad = C5cantidad;
	}
	public double getC6precio_venta(){
		return C6precio_venta;
	}
	public void setC6precio_venta(double C6precio_venta){
		this.C6precio_venta = C6precio_venta;
	}
	public double getC7precio_compra(){
		return C7precio_compra;
	}
	public void setC7precio_compra(double C7precio_compra){
		this.C7precio_compra = C7precio_compra;
	}
	public int getC8iva(){
		return C8iva;
	}
	public void setC8iva(int C8iva){
		this.C8iva = C8iva;
	}
	public double getC9total_exenta(){
		return C9total_exenta;
	}
	public void setC9total_exenta(double C9total_exenta){
		this.C9total_exenta = C9total_exenta;
	}
	public double getC10total_gravada_5(){
		return C10total_gravada_5;
	}
	public void setC10total_gravada_5(double C10total_gravada_5){
		this.C10total_gravada_5 = C10total_gravada_5;
	}
	public double getC11total_gravada_10(){
		return C11total_gravada_10;
	}
	public void setC11total_gravada_10(double C11total_gravada_10){
		this.C11total_gravada_10 = C11total_gravada_10;
	}
	public String getC12cuenta_exenta(){
		return C12cuenta_exenta;
	}
	public void setC12cuenta_exenta(String C12cuenta_exenta){
		this.C12cuenta_exenta = C12cuenta_exenta;
	}
	public String getC13cuenta_5(){
		return C13cuenta_5;
	}
	public void setC13cuenta_5(String C13cuenta_5){
		this.C13cuenta_5 = C13cuenta_5;
	}
	public String getC14cuenta_10(){
		return C14cuenta_10;
	}
	public void setC14cuenta_10(String C14cuenta_10){
		this.C14cuenta_10 = C14cuenta_10;
	}
	public int getC15fk_idfactura_autoimpresor(){
		return C15fk_idfactura_autoimpresor;
	}
	public void setC15fk_idfactura_autoimpresor(int C15fk_idfactura_autoimpresor){
		this.C15fk_idfactura_autoimpresor = C15fk_idfactura_autoimpresor;
	}
	public int getC16fk_idproducto(){
		return C16fk_idproducto;
	}
	public void setC16fk_idproducto(int C16fk_idproducto){
		this.C16fk_idproducto = C16fk_idproducto;
	}
	public String toString() {
		return "item_factura_auto(" + ",iditem_factura_auto=" + C1iditem_factura_auto + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,descripcion=" + C4descripcion + " ,cantidad=" + C5cantidad + " ,precio_venta=" + C6precio_venta + " ,precio_compra=" + C7precio_compra + " ,iva=" + C8iva + " ,total_exenta=" + C9total_exenta + " ,total_gravada_5=" + C10total_gravada_5 + " ,total_gravada_10=" + C11total_gravada_10 + " ,cuenta_exenta=" + C12cuenta_exenta + " ,cuenta_5=" + C13cuenta_5 + " ,cuenta_10=" + C14cuenta_10 + " ,fk_idfactura_autoimpresor=" + C15fk_idfactura_autoimpresor + " ,fk_idproducto=" + C16fk_idproducto + " )";
	}
}
