package modelo;

import modelo.notificaciones.Notificaciones;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Empleado extends Usuario implements java.io.Serializable {

    private PriorityQueue<Notificaciones> pendientes;
    private PriorityQueue<Notificaciones> aprobadas;
    private PriorityQueue<Notificaciones> denegadas;
    private PriorityQueue<Cuadricula> cuadriculas;
    private String departamento;

    public Empleado()
    {

    }

    public void setPendientes (Set<Notificaciones> pendientes)
    {
        this.pendientes = new PriorityQueue<Notificaciones>(pendientes);
    }

    public void setAprobadas (Set<Notificaciones> aprobadas)
    {
        this.aprobadas = new PriorityQueue<Notificaciones>(0, new ComparadorNRecientes());
        this.aprobadas.addAll(aprobadas);
    }

    public void setDenegadas (Set<Notificaciones> denegadas)
    {
        this.denegadas = new PriorityQueue<Notificaciones>(denegadas);
    }

    public Set<Notificaciones> getPendientes ()
    {
        return new TreeSet<Notificaciones>(this.pendientes);
    }

    public Set<Notificaciones> getAprobadas ()
    {
        return new TreeSet<Notificaciones>(this.aprobadas);
    }

    public Set<Notificaciones> getDenegadas ()
    {
        return new TreeSet<Notificaciones>(this.denegadas);
    }

    public void setDepartamento (String dep)
    {
        this.departamento = dep;
    }

    public String getDepartamento ()
    {
        return this.departamento;
    }

    public void setCuadriculas(Set<Cuadricula> c)
    {
        cuadriculas = new PriorityQueue<Cuadricula>(c);
    }

    public Set<Cuadricula> getCuadriculas()
    {
        return new TreeSet(cuadriculas);
    }

    private class ComparadorNRecientes implements Comparator<Notificaciones>
    {
        public int compare(Notificaciones n1, Notificaciones n2)
        {
            return n2.getFecha().compareTo(n1.getFecha());
        }
    }

}
