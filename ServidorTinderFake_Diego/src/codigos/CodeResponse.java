
package codigos;

/**
 *
 * @author Diego
 */
public class CodeResponse {
    
    //Codigo de error
    public static final short ERROR_CODE = -1;
    
    //Codigos Login
    public static final short LOGIN_CODE = 1;   
    public static final short LOGIN_CORRECTO_CODE = 2;   
    public static final short LOGIN_NO_ACTIVADO_CODE = 16;   
    
    //Codigo Sign UP
    public static final short SIGNUP_CODE = 3; 
    public static final short SIGNUP_CORRECTO_CODE = 4;   
    
    //Codigo Prefrencias
    public static final short PREFS_CODE = 5; 
    public static final short PREFS_EXSTEN_CODE = 6;   
    public static final short PREFS_ACTUALIZAR_CODE = 7;   
    public static final short PREFS_CREAR_CODE = 8;  
    public static final short PREFS_CORRECTO_CODE = 9;   
    
    //Codigo Admin
    public static final short ADMIN_CODE = 10;  
    public static final short ADD_CODE = 11;  
    public static final short ACTIVAR_CODE = 12;  
    public static final short DELETE_CODE = 13;  
    public static final short ASC_CODE = 14;  
    public static final short DESC_CODE = 15;  
    public static final short ADMIN_EXITO_CODE = 17; 
    
}
