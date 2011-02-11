package modelo;

import java.io.Serializable;
import java.util.PriorityQueue;
import modelo.notificacion.Notificacion;
import modelo.notificacion.Notificacion;
import modelo.Usuario;

// Siguiente paso:
// Crear notificaciones hijas e implementar método notificar()

public class Empleado extends Usuario implements Serializable {
    private PriorityQueue<Notificacion> pendientes;
    private PriorityQueue<Notificacion> aprobadas;
    private PriorityQueue<Notificacion> denegadas;

    public Empleado() {
        pendientes = new PriorityQueue<Notificacion>();
        aprobadas = new PriorityQueue<Notificacion>();
        denegadas = new PriorityQueue<Notificacion>();
    }

    public void notificar(Notificacion n) {
        
    }

    

}
