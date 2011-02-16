package modelo;
// Generated 16-feb-2011 10:22:20 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notificaciones generated by hbm2java
 */
public class Notificaciones  implements java.io.Serializable {


     private int idNotificaciones;
     private Usuario usuarioByRemitente;
     private Cuadricula cuadricula;
     private Usuario usuarioByDestinatario;
     private String tipo;
     private Date fecha;
     private Set notificacionDenegadas = new HashSet(0);
     private Set notificacionPendientes = new HashSet(0);

    public Notificaciones() {
    }

	
    public Notificaciones(int idNotificaciones, Usuario usuarioByRemitente, Cuadricula cuadricula, Usuario usuarioByDestinatario, Date fecha) {
        this.idNotificaciones = idNotificaciones;
        this.usuarioByRemitente = usuarioByRemitente;
        this.cuadricula = cuadricula;
        this.usuarioByDestinatario = usuarioByDestinatario;
        this.fecha = fecha;
    }
    public Notificaciones(int idNotificaciones, Usuario usuarioByRemitente, Cuadricula cuadricula, Usuario usuarioByDestinatario, String tipo, Date fecha, Set notificacionDenegadas, Set notificacionPendientes) {
       this.idNotificaciones = idNotificaciones;
       this.usuarioByRemitente = usuarioByRemitente;
       this.cuadricula = cuadricula;
       this.usuarioByDestinatario = usuarioByDestinatario;
       this.tipo = tipo;
       this.fecha = fecha;
       this.notificacionDenegadas = notificacionDenegadas;
       this.notificacionPendientes = notificacionPendientes;
    }
   
    public int getIdNotificaciones() {
        return this.idNotificaciones;
    }
    
    public void setIdNotificaciones(int idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }
    public Usuario getUsuarioByRemitente() {
        return this.usuarioByRemitente;
    }
    
    public void setUsuarioByRemitente(Usuario usuarioByRemitente) {
        this.usuarioByRemitente = usuarioByRemitente;
    }
    public Cuadricula getCuadricula() {
        return this.cuadricula;
    }
    
    public void setCuadricula(Cuadricula cuadricula) {
        this.cuadricula = cuadricula;
    }
    public Usuario getUsuarioByDestinatario() {
        return this.usuarioByDestinatario;
    }
    
    public void setUsuarioByDestinatario(Usuario usuarioByDestinatario) {
        this.usuarioByDestinatario = usuarioByDestinatario;
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




}


