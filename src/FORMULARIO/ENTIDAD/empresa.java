	package FORMULARIO.ENTIDAD;
public class empresa {

//---------------DECLARAR VARIABLES---------------
private int C1idempresa;
private String C2fecha_creado;
private String C3creado_por;
private String C4razon_social;
private int C5ruc;
private int C6dv;
private String C7direccion;
private String C8celular;
private boolean C9activo;
private String C10mensaje_final;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public empresa() {
		setTb_empresa("empresa");
		setId_idempresa("idempresa");
	}
	public static String getTb_empresa(){
		return nom_tabla;
	}
	public static void setTb_empresa(String nom_tabla){
		empresa.nom_tabla = nom_tabla;
	}
	public static String getId_idempresa(){
		return nom_idtabla;
	}
	public static void setId_idempresa(String nom_idtabla){
		empresa.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idempresa(){
		return C1idempresa;
	}
	public void setC1idempresa(int C1idempresa){
		this.C1idempresa = C1idempresa;
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
	public String getC4razon_social(){
		return C4razon_social;
	}
	public void setC4razon_social(String C4razon_social){
		this.C4razon_social = C4razon_social;
	}
	public int getC5ruc(){
		return C5ruc;
	}
	public void setC5ruc(int C5ruc){
		this.C5ruc = C5ruc;
	}
	public int getC6dv(){
		return C6dv;
	}
	public void setC6dv(int C6dv){
		this.C6dv = C6dv;
	}
	public String getC7direccion(){
		return C7direccion;
	}
	public void setC7direccion(String C7direccion){
		this.C7direccion = C7direccion;
	}
	public String getC8celular(){
		return C8celular;
	}
	public void setC8celular(String C8celular){
		this.C8celular = C8celular;
	}
	public boolean getC9activo(){
		return C9activo;
	}
	public void setC9activo(boolean C9activo){
		this.C9activo = C9activo;
	}
	public String getC10mensaje_final(){
		return C10mensaje_final;
	}
	public void setC10mensaje_final(String C10mensaje_final){
		this.C10mensaje_final = C10mensaje_final;
	}
	public String toString() {
		return "empresa(" + ",idempresa=" + C1idempresa + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,razon_social=" + C4razon_social + " ,ruc=" + C5ruc + " ,dv=" + C6dv + " ,direccion=" + C7direccion + " ,celular=" + C8celular + " ,activo=" + C9activo + " ,mensaje_final=" + C10mensaje_final + " )";
	}
}
