package modelo;

import modelo.notificaciones.Notificaciones;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import modelo.notificaciones.NotificacionAprobada;
import modelo.notificaciones.NotificacionDenegada;
import modelo.notificaciones.NotificacionPendiente;

public class Empleado extends Usuario implements java.io.Serializable {

    
    private Set<NotificacionDenegada> denegadas;
    private Set<NotificacionPendiente> pendientes;
    private Set<NotificacionAprobada> aprobadas;
    
    private Set cuadriculas;
    private String departamento;

    public Empleado()
    {

    }

    public void setPendientes (Set pendientes)
    {
        this.pendientes = pendientes;
    }

    public void setAprobadas (Set aprobadas)
    {
        this.aprobadas=aprobadas;
    }

    public void setDenegadas (Set denegadas)
    {
        this.denegadas = denegadas;
    }

    public Set getPendientes ()
    {
        return this.pendientes;
    }

    public Set getAprobadas ()
    {
        return this.aprobadas;
    }

    public Set getDenegadas ()
    {
        return this.denegadas;
    }

    public void setDepartamento (String dep)
    {
        this.departamento = dep;
    }

    public String getDepartamento ()
    {
        return this.departamento;
    }

     public void setCuadriculas(Set c)
    {
        cuadriculas = c;
    }

    public Set getCuadriculas()
    {
        return this.cuadriculas;
    }

     public void addCuadriculas(Cuadricula c){
        c.setUsuario(this);
        this.cuadriculas.add(c);
    }

     public void addAprobadas(NotificacionAprobada c){
        c.setDestinatario(this);
        this.aprobadas.add(c);
    }


    private class ComparadorNRecientes implements Comparator<Notificaciones>
    {
        public int compare(Notificaciones n1, Notificaciones n2)
        {
            return n2.getFecha().compareTo(n1.getFecha());
        }
    }

}
