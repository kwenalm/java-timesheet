package modelo.notificacion;

import java.util.Date;
import modelo.Usuario;

public class Notificacion implements Comparable<Notificacion> {
    private Usuario origen;
    private Usuario destino;
    private Date fecha;

    public int compareTo(Notificacion n) {
        return fecha.compareTo(n.fecha);
    }
}