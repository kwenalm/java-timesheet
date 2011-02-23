package modelo.notificaciones;

public class NotificacionDenegada extends Notificaciones implements java.io.Serializable {

      //private int idNotificaciones;
     //private Notificaciones notificaciones;
     private String motivo;

    public NotificacionDenegada() {
    }

/*
    public NotificacionDenegada(int idNotificaciones, Notificaciones notificaciones) {
        this.idNotificaciones = idNotificaciones;
        this.notificaciones = notificaciones;
    }
    public NotificacionDenegada(int idNotificaciones, Notificaciones notificaciones, String motivo) {
       this.idNotificaciones = idNotificaciones;
       this.notificaciones = notificaciones;
       this.motivo = motivo;
    }
  */
    //public int getIdNotificaciones() {
      //  return this.idNotificaciones;
    //}
//
   //public void setIdNotificaciones(int idNotificaciones) {
     //   this.idNotificaciones = idNotificaciones;
    //}
    /*
    public Notificaciones getNotificaciones() {
        return this.notificaciones;
    }
    
    public void setNotificaciones(Notificaciones notificaciones) {
        this.notificaciones = notificaciones;
    }

     */
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }




}


