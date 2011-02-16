/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import modelo.notificaciones.Notificaciones;

/**
 *
 * @author Java I
 */
public class Jefe extends Usuario implements Serializable {

    private String departamento;
    private PriorityQueue<Notificaciones> notificaciones;

    public Jefe() {

    }

    public void setDepartamento(String s) {
        departamento = s;
    }

    public void setNotificaciones(List<Notificaciones> l) {
        notificaciones = new PriorityQueue<Notificaciones>(0,new ComparadorNJefe());
        notificaciones.addAll(l);
    }

    public String getDepartamento() {
        return departamento;
    }

    public List<Notificaciones> getNotificaciones() {
        return new ArrayList<Notificaciones>(notificaciones);
    }
}
