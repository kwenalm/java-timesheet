package modelo.notificaciones;

public class NotificacionAprobada extends Notificaciones implements java.io.Serializable {

    private String motivo;


    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
