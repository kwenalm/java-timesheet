package modelo;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import modelo.notificaciones.Notificaciones;
import java.util.Comparator;

public class Jefe extends Usuario implements Serializable {

    private String departamento;
    private Set notificaciones;

    public Jefe() {

    }

    public void setDepartamento(String s) {
        departamento = s;
    }

    public void setNotificaciones(Set l) {
        notificaciones = new TreeSet(
                new Comparator<Notificaciones>(){
                    public int compare (Notificaciones n1, Notificaciones n2)
                    {
                        int result =0;
                        //String nombre1 = n1.getRemitente().getApellido1()+ " " + n1.getRemitente().getApellido2() + ", " + n1.getRemitente().getNombre();
                        //String nombre2 = n2.getRemitente().getApellido1()+ " " + n2.getRemitente().getApellido2() + ", " + n2.getRemitente().getNombre();
                       // int result = nombre1.compareToIgnoreCase(nombre2);
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

    public Set getNotificaciones() {
        return new TreeSet(notificaciones);
    }
}
