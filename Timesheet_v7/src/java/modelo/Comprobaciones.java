/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Date;
import modelo.notificaciones.Notificaciones;
import modelo.Cuadricula;
import modelo.Usuario;
/**
 *
 * @author Java I
 */
public class Comprobaciones {
    Autentificacion autentificacion;
    private String usuario;
    private String clave;

    public String comprobar(){
        autentificacion=new Autentificacion();
        autentificacion.setNif(usuario);
        autentificacion.setClave(clave);
        // Si existe se ha de recuperar el tipo del usuario para entrar
        //de una forma u otra al sistema.

        Empleado pedro = (Empleado)dao.Conexion.getEntidad("33445566N", modelo.Usuario.class);
        pedro.setCuadriculas(null);
        pedro.setDenegadas(null);
        pedro.setAprobadas(null);
        pedro.setPendientes(null);

        Cuadricula c = new Cuadricula();
        c.setIdCuadricula(3);
        c.setEstado("ACEPTADA");
        c.setFechaInicio(new Date());
        c.setUsuario(pedro);

        //Empleado e = (Empleado) dao.Conexion.getEntidad(pedro.getNif(), modelo.Empleado.class);
       // System.out.println(e);
//        Notificaciones n = new Notificaciones();
//        n.setIdNotificaciones(1);
//        n.set
        //String c = "caca";

        dao.Conexion.almacenaEntidad(c);

        return autentificacion.existe();
    }
    
    public Autentificacion getAutentificacion() {
        return autentificacion;
    }

    public void setAutentificacion(Autentificacion autentificacion) {
        this.autentificacion = autentificacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
