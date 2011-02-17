package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import modelo.notificaciones.Notificaciones;
import java.util.Comparator;

public class Jefe extends Usuario implements Serializable {

    private String departamento;
    private PriorityQueue<Notificaciones> notificaciones;

    public Jefe() {

    }

    public void setDepartamento(String s) {
        departamento = s;
    }

    public void setNotificaciones(List<Notificaciones> l) {
        notificaciones = new PriorityQueue<Notificaciones>(0,
                new Comparator<Notificaciones>(){
                    public int compare (Notificaciones n1, Notificaciones n2)
                    {
                        String nombre1 = n1.getUsuarioByRemitente().getApellido1()+ " " + n1.getUsuarioByRemitente().getApellido2() + ", " + n1.getUsuarioByRemitente().getNombre(); 
                        String nombre2 = n2.getUsuarioByRemitente().getApellido1()+ " " + n2.getUsuarioByRemitente().getApellido2() + ", " + n2.getUsuarioByRemitente().getNombre();
                        int result = nombre1.compareToIgnoreCase(nombre2);
                        if (result == 0)
                        {
                            return n1.getFecha().compareTo(n2.getFecha());
                        }
                        else
                        {
                            return result;
                        }
                    }
                });
        notificaciones.addAll(l);
    }

    public String getDepartamento() {
        return departamento;
    }

    public List<Notificaciones> getNotificaciones() {
        return new ArrayList<Notificaciones>(notificaciones);
    }
}
