package modelo.notificaciones;
// Generated 16-feb-2011 10:22:20 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * NotificacionPendiente generated by hbm2java
 */
public class NotificacionPendiente extends Notificaciones implements java.io.Serializable {


     // private int idNotificaciones;
    // private Notificaciones notificaciones;
     private Date plazo;

    public NotificacionPendiente() {
    }
/*
    public NotificacionPendiente(int idNotificaciones, Notificaciones notificaciones, Date plazo) {
       this.idNotificaciones = idNotificaciones;
       this.notificaciones = notificaciones;
       this.plazo = plazo;
    }
  */
//    public int getIdNotificaciones() {
//        return this.idNotificaciones;
//    }
//
//    public void setIdNotificaciones(int idNotificaciones) {
//        this.idNotificaciones = idNotificaciones;
//    }
    /*
    public Notificaciones getNotificaciones() {
        return this.notificaciones;
    }
    
    public void setNotificaciones(Notificaciones notificaciones) {
        this.notificaciones = notificaciones;
    }
     */
    public Date getPlazo() {
        return this.plazo;
    }
    
    public void setPlazo(Date plazo) {
        this.plazo = plazo;
    }




}


