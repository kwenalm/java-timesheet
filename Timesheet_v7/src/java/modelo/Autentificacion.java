package modelo;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Autentificacion  implements java.io.Serializable {

     private String nif;
     private String clave;   // la clave debe de estar cifrada
     //private Usuario usuario;

    public Autentificacion() {

    }

    public Autentificacion(String nif, Usuario usuario, String clave) {
       this.nif = nif;
       // this.usuario = usuario;
       this.clave = clave;
    }
   
    public String getNif() {
        return this.nif;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    /*
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
     */

    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }


    public String existe(){
        Autentificacion a = dao.Conexion.getEntidad(nif, Autentificacion.class);
        String result;
        if (a!=null)
        {
            if (clave.equals(a.getClave()))
            {
                Usuario user = dao.Conexion.getEntidad(nif,modelo.Usuario.class );
                if (user instanceof Empleado)
                {
                    result = "empleado";
                }
                else if (user instanceof Jefe)
                {
                    result = "jefe";
                }
                else
                {
                    result = "administrador";
                }
            }
            else
            {
                result = "noexiste";
            }
        }
        else
        {
            result = "noexiste";
        }
        return result;
    }

}


