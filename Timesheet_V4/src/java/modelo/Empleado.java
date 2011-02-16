/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;
import modelo.notificaciones.Notificaciones;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 *
 * @author Java I
 */
public class Empleado extends Usuario implements java.io.Serializable {

    private PriorityQueue<Notificaciones> pendientes;
    private PriorityQueue<Notificaciones> aprobadas;
    private PriorityQueue<Notificaciones> denegadas;
    private String departamento;

    public Empleado()
    {

    }

    public void setPendientes (List<Notificaciones> pendientes)
    {
        this.pendientes = new PriorityQueue<Notificaciones>(pendientes);
    }
    
    public void setAprobadas (List<Notificaciones> aprobadas)
    {
        this.aprobadas = new PriorityQueue<Notificaciones>(0, new ComparadorNRecientes());
        this.aprobadas.addAll(aprobadas);
    }
    
    public void setDenegadas (List<Notificaciones> denegadas)
    {
        this.denegadas = new PriorityQueue<Notificaciones>(denegadas);
    }
    
    public List<Notificaciones> getPendientes ()
    {
        return new ArrayList<Notificaciones>(this.pendientes);
    }
    
    public List<Notificaciones> getAprobadas ()
    {
        return new ArrayList<Notificaciones>(this.aprobadas);
    }
    
    public List<Notificaciones> getDenegadas ()
    {
        return new ArrayList<Notificaciones>(this.denegadas);
    }
    
    public void setDepartamento (String dep)
    {
        this.departamento = dep;
    }
    
    public String getDepartamento ()
    {
        return this.departamento;
    }

    private class ComparadorNRecientes implements Comparator<Notificaciones>
    {
        public int compare(Notificaciones n1, Notificaciones n2)
        {
            return n2.getFecha().compareTo(n1.getFecha());
        }
    }
}
