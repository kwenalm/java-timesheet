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
     /*private String tipo;*/
     private Date fecha;
     /*private Set notificacionDenegadas = new HashSet(0);
     private Set notificacionPendientes = new HashSet(0);
*/
    public Notificaciones() {
    }

/*
    public Notificaciones(int idNotificaciones, Usuario usuarioByRemitente, Cuadricula cuadricula, Usuario usuarioByDestinatario, Date fecha) {
        this.idNotificaciones = idNotificaciones;
        this.remitente = usuarioByRemitente;
        this.cuadricula = cuadricula;
        this.destinatario = usuarioByDestinatario;
        this.fecha = fecha;
    }
    public Notificaciones(int idNotificaciones, Usuario usuarioByRemitente, Cuadricula cuadricula, Usuario usuarioByDestinatario, String tipo, Date fecha, Set notificacionDenegadas, Set notificacionPendientes) {
       this.idNotificaciones = idNotificaciones;
       this.remitente = usuarioByRemitente;
       this.cuadricula = cuadricula;
       this.destinatario = usuarioByDestinatario;
       /*this.tipo = tipo;
       this.fecha = fecha;
      /* this.notificacionDenegadas = notificacionDenegadas;
       this.notificacionPendientes = notificacionPendientes;
    }
  */
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
    /*
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }*/

    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /*
    public Set getNotificacionDenegadas() {
        return this.notificacionDenegadas;
    }
    
    public void setNotificacionDenegadas(Set notificacionDenegadas) {
        this.notificacionDenegadas = notificacionDenegadas;
    }
    public Set getNotificacionPendientes() {
        return this.notificacionPendientes;
    }
    
    public void setNotificacionPendientes(Set notificacionPendientes) {
        this.notificacionPendientes = notificacionPendientes;
    }
*/
    // Orden natural: Primero las fechas más antiguas
    public int compareTo (Notificaciones n)
    {
        return fecha.compareTo(n.fecha);
    }

}


