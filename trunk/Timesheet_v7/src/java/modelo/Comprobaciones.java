/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Date;
import modelo.notificaciones.Notificaciones;
import modelo.Cuadricula;
import modelo.Usuario;
import java.util.Set;
import java.util.TreeSet;
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
        Cuadricula cuadriculas = (Cuadricula)pedro.getCuadriculas().iterator().next();
        DatoCuadricula datos = new DatoCuadricula();
        datos.setDia(new Date());
        datos.setDpto("cuentas");
        datos.setHoras(10);
        Set datoss = new TreeSet();
        datoss.add(datos);
       // cuadriculas.setDatosCuadricula(datoss);
        pedro.setDenegadas(null);
        pedro.setAprobadas(null);
        pedro.setPendientes(null);

        Cuadricula c = new Cuadricula();
        c.setIdCuadricula(5);
        
        c.setFechaInicio(new Date());
        c.setUsuario(pedro);
        c.setEstado(TipoCuadricula.PENDIENTE);
       
       // System.out.println(e);
//        Notificaciones n = new Notificaciones();
//        n.setIdNotificaciones(1);
//        n.set
        //String c = "caca";

  //      dao.Conexion.almacenaEntidad(cuadriculas);

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
