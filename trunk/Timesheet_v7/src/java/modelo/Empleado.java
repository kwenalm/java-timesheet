package modelo;

import modelo.notificaciones.Notificaciones;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Set;
import java.util.TreeSet;

public class Empleado extends Usuario implements java.io.Serializable {

    private Set pendientes;
    private Set aprobadas;
    private Set denegadas;
    private PriorityQueue<Cuadricula> cuadriculas;
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
        this.aprobadas = new TreeSet( new ComparadorNRecientes());
        this.aprobadas.addAll(aprobadas);
    }

    public void setDenegadas (Set denegadas)
    {
        this.denegadas = denegadas;
    }

    public Set getPendientes ()
    {
        return new TreeSet(this.pendientes);
    }

    public Set getAprobadas ()
    {
        return new TreeSet(this.aprobadas);
    }

    public Set getDenegadas ()
    {
        return new TreeSet(this.denegadas);
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
