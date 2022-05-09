	package FORMULARIO.ENTIDAD;
public class producto_cuenta {

//---------------DECLARAR VARIABLES---------------
private int C1idproducto_cuenta;
private String C2fecha_creado;
private String C3creado_por;
private String C4descripcion;
private int C5iva;
private boolean C6activo;
private static String nom_tabla;
private static String nom_idtabla;
private static String nom_nomtabla;

//---------------TABLA-ID---------------
	public producto_cuenta() {
		setTb_producto_cuenta("producto_cuenta");
		setId_idproducto_cuenta("idproducto_cuenta");
                setNom_nomtabla("descripcion");
	}

    public static String getNom_nomtabla() {
        return nom_nomtabla;
    }

    public static void setNom_nomtabla(String nom_nomtabla) {
        producto_cuenta.nom_nomtabla = nom_nomtabla;
    }
        
	public static String getTb_producto_cuenta(){
		return nom_tabla;
	}
	public static void setTb_producto_cuenta(String nom_tabla){
		producto_cuenta.nom_tabla = nom_tabla;
	}
	public static String getId_idproducto_cuenta(){
		return nom_idtabla;
	}
	public static void setId_idproducto_cuenta(String nom_idtabla){
		producto_cuenta.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idproducto_cuenta(){
		return C1idproducto_cuenta;
	}
	public void setC1idproducto_cuenta(int C1idproducto_cuenta){
		this.C1idproducto_cuenta = C1idproducto_cuenta;
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
	public int getC5iva(){
		return C5iva;
	}
	public void setC5iva(int C5iva){
		this.C5iva = C5iva;
	}
	public boolean getC6activo(){
		return C6activo;
	}
	public void setC6activo(boolean C6activo){
		this.C6activo = C6activo;
	}
	public String toString() {
		return "producto_cuenta(" + ",idproducto_cuenta=" + C1idproducto_cuenta + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,descripcion=" + C4descripcion + " ,iva=" + C5iva + " ,activo=" + C6activo + " )";
	}
}
