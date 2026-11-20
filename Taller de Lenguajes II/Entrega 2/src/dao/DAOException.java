package dao; 
public class DAOException extends RuntimeException { 
    
    public DAOException(String mensaje) { 
        super(mensaje); 
    } 

    public DAOException(String mensaje, Throwable causa) { 
        super(mensaje, causa); 
    } 
}