	package FORMULARIO.ENTIDAD;
public class cajero {

//---------------DECLARAR VARIABLES---------------
private int C1idcajero;
private String C2fecha_creado;
private String C3creado_por;
private String C4nombre;
private String C5cedula;
private String C6direccion;
private String C7telefono;
private boolean C8activo;
private static String nom_tabla;
private static String nom_idtabla;
private static String nom_nombre;
//---------------TABLA-ID---------------
	public cajero() {
		setTb_cajero("cajero");
		setId_idcajero("idcajero");
                setNom_nombre("nombre");
	}
	public static String getTb_cajero(){
		return nom_tabla;
	}
	public static void setTb_cajero(String nom_tabla){
		cajero.nom_tabla = nom_tabla;
	}
	public static String getId_idcajero(){
		return nom_idtabla;
	}
	public static void setId_idcajero(String nom_idtabla){
		cajero.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idcajero(){
		return C1idcajero;
	}
	public void setC1idcajero(int C1idcajero){
		this.C1idcajero = C1idcajero;
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
	public String getC4nombre(){
		return C4nombre;
	}
	public void setC4nombre(String C4nombre){
		this.C4nombre = C4nombre;
	}
	public String getC5cedula(){
		return C5cedula;
	}
	public void setC5cedula(String C5cedula){
		this.C5cedula = C5cedula;
	}
	public String getC6direccion(){
		return C6direccion;
	}
	public void setC6direccion(String C6direccion){
		this.C6direccion = C6direccion;
	}
	public String getC7telefono(){
		return C7telefono;
	}
	public void setC7telefono(String C7telefono){
		this.C7telefono = C7telefono;
	}
	public boolean getC8activo(){
		return C8activo;
	}
	public void setC8activo(boolean C8activo){
		this.C8activo = C8activo;
	}

    public static String getNom_nombre() {
        return nom_nombre;
    }

    public static void setNom_nombre(String nom_nombre) {
        cajero.nom_nombre = nom_nombre;
    }
	public String toString() {
		return "cajero(" + ",idcajero=" + C1idcajero + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,nombre=" + C4nombre + " ,cedula=" + C5cedula + " ,direccion=" + C6direccion + " ,telefono=" + C7telefono + " ,activo=" + C8activo + " )";
	}
}
