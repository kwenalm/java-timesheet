/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

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
