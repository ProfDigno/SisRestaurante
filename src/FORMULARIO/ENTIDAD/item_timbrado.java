	package FORMULARIO.ENTIDAD;
public class item_timbrado {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_timbrado;
private String C2fecha_creado;
private String C3creado_por;
private int C4fk_idempresa;
private int C5fk_idtimbrado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_timbrado() {
		setTb_item_timbrado("item_timbrado");
		setId_iditem_timbrado("iditem_timbrado");
	}
	public static String getTb_item_timbrado(){
		return nom_tabla;
	}
	public static void setTb_item_timbrado(String nom_tabla){
		item_timbrado.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_timbrado(){
		return nom_idtabla;
	}
	public static void setId_iditem_timbrado(String nom_idtabla){
		item_timbrado.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_timbrado(){
		return C1iditem_timbrado;
	}
	public void setC1iditem_timbrado(int C1iditem_timbrado){
		this.C1iditem_timbrado = C1iditem_timbrado;
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
	public int getC4fk_idempresa(){
		return C4fk_idempresa;
	}
	public void setC4fk_idempresa(int C4fk_idempresa){
		this.C4fk_idempresa = C4fk_idempresa;
	}
	public int getC5fk_idtimbrado(){
		return C5fk_idtimbrado;
	}
	public void setC5fk_idtimbrado(int C5fk_idtimbrado){
		this.C5fk_idtimbrado = C5fk_idtimbrado;
	}
	public String toString() {
		return "item_timbrado(" + ",iditem_timbrado=" + C1iditem_timbrado + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,fk_idempresa=" + C4fk_idempresa + " ,fk_idtimbrado=" + C5fk_idtimbrado + " )";
	}
}
