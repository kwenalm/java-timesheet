package modelo.notificaciones;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import modelo.Cuadricula;
import modelo.Usuario;

public class Notificaciones  implements java.io.Serializable, Comparable<Notificaciones>{

     private int idNotificaciones;
     private Usuario remitente;
     private Usuario destinatario;
     private Cuadricula cuadricula;
     private String tipo;
     private Date fecha;

    public Notificaciones() {
    }

    public int getIdNotificaciones() {
        return this.idNotificaciones;
    }
    
    public void setIdNotificaciones(int idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }
    
    public Usuario getRemitente() {
        return this.remitente;
    }
    
    public void setRemitente(Usuario usuarioByRemitente) {
        this.remitente = usuarioByRemitente;
    }
    public Cuadricula getCuadricula() {
        return this.cuadricula;
    }
    
    public void setCuadricula(Cuadricula cuadricula) {
        this.cuadricula = cuadricula;
    }
    public Usuario getDestinatario() {
        return this.destinatario;
    }
    
    public void setDestinatario(Usuario usuarioByDestinatario) {
        this.destinatario = usuarioByDestinatario;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
 
    // Orden natural: Primero las fechas más antiguas
    public int compareTo (Notificaciones n)
    {
        return fecha.compareTo(n.fecha);
    }

}


