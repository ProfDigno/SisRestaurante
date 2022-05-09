	package FORMULARIO.ENTIDAD;
public class timbrado {

//---------------DECLARAR VARIABLES---------------
private int C1idtimbrado;
private String C2fecha_creado;
private String C3creado_por;
private String C4mac_pc;
private int C5numero;
private String C6fecha_inicio;
private String C7fecha_fin;
private int C8cod_establecimiento;
private int C9punto_expedicion;
private int C10numero_inicial;
private int C11numero_final;
private int C12numero_actual;
private boolean C13activo;
private boolean C14es_vencido;
private int C15numero_limite;
private int C16dias_limite;
private int C17numero_caja;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public timbrado() {
		setTb_timbrado("timbrado");
		setId_idtimbrado("idtimbrado");
	}
	public static String getTb_timbrado(){
		return nom_tabla;
	}
	public static void setTb_timbrado(String nom_tabla){
		timbrado.nom_tabla = nom_tabla;
	}
	public static String getId_idtimbrado(){
		return nom_idtabla;
	}
	public static void setId_idtimbrado(String nom_idtabla){
		timbrado.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtimbrado(){
		return C1idtimbrado;
	}
	public void setC1idtimbrado(int C1idtimbrado){
		this.C1idtimbrado = C1idtimbrado;
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
	public String getC4mac_pc(){
		return C4mac_pc;
	}
	public void setC4mac_pc(String C4mac_pc){
		this.C4mac_pc = C4mac_pc;
	}
	public int getC5numero(){
		return C5numero;
	}
	public void setC5numero(int C5numero){
		this.C5numero = C5numero;
	}
	public String getC6fecha_inicio(){
		return C6fecha_inicio;
	}
	public void setC6fecha_inicio(String C6fecha_inicio){
		this.C6fecha_inicio = C6fecha_inicio;
	}
	public String getC7fecha_fin(){
		return C7fecha_fin;
	}
	public void setC7fecha_fin(String C7fecha_fin){
		this.C7fecha_fin = C7fecha_fin;
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
	public int getC10numero_inicial(){
		return C10numero_inicial;
	}
	public void setC10numero_inicial(int C10numero_inicial){
		this.C10numero_inicial = C10numero_inicial;
	}
	public int getC11numero_final(){
		return C11numero_final;
	}
	public void setC11numero_final(int C11numero_final){
		this.C11numero_final = C11numero_final;
	}
	public int getC12numero_actual(){
		return C12numero_actual;
	}
	public void setC12numero_actual(int C12numero_actual){
		this.C12numero_actual = C12numero_actual;
	}
	public boolean getC13activo(){
		return C13activo;
	}
	public void setC13activo(boolean C13activo){
		this.C13activo = C13activo;
	}
	public boolean getC14es_vencido(){
		return C14es_vencido;
	}
	public void setC14es_vencido(boolean C14es_vencido){
		this.C14es_vencido = C14es_vencido;
	}
	public int getC15numero_limite(){
		return C15numero_limite;
	}
	public void setC15numero_limite(int C15numero_limite){
		this.C15numero_limite = C15numero_limite;
	}
	public int getC16dias_limite(){
		return C16dias_limite;
	}
	public void setC16dias_limite(int C16dias_limite){
		this.C16dias_limite = C16dias_limite;
	}
	public int getC17numero_caja(){
		return C17numero_caja;
	}
	public void setC17numero_caja(int C17numero_caja){
		this.C17numero_caja = C17numero_caja;
	}
	public String toString() {
		return "timbrado(" + ",idtimbrado=" + C1idtimbrado + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,mac_pc=" + C4mac_pc + " ,numero=" + C5numero + " ,fecha_inicio=" + C6fecha_inicio + " ,fecha_fin=" + C7fecha_fin + " ,cod_establecimiento=" + C8cod_establecimiento + " ,punto_expedicion=" + C9punto_expedicion + " ,numero_inicial=" + C10numero_inicial + " ,numero_final=" + C11numero_final + " ,numero_actual=" + C12numero_actual + " ,activo=" + C13activo + " ,es_vencido=" + C14es_vencido + " ,numero_limite=" + C15numero_limite + " ,dias_limite=" + C16dias_limite + " ,numero_caja=" + C17numero_caja + " )";
	}
}
