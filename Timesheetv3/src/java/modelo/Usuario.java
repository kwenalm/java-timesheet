/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "Usuario", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nif")
    private String nif;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "departamento")
    private String departamento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Autentificacion autentificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remitente")
    private Collection<Notificaciones> notificacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinatario")
    private Collection<Notificaciones> notificacionesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nif")
    private Collection<Cuadricula> cuadriculaCollection;

    public Usuario() {
    }

    public Usuario(String nif) {
        this.nif = nif;
    }

    public Usuario(String nif, String nombre, String apellido1, String tipo) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.tipo = tipo;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Autentificacion getAutentificacion() {
        return autentificacion;
    }

    public void setAutentificacion(Autentificacion autentificacion) {
        this.autentificacion = autentificacion;
    }

    public Collection<Notificaciones> getNotificacionesCollection() {
        return notificacionesCollection;
    }

    public void setNotificacionesCollection(Collection<Notificaciones> notificacionesCollection) {
        this.notificacionesCollection = notificacionesCollection;
    }

    public Collection<Notificaciones> getNotificacionesCollection1() {
        return notificacionesCollection1;
    }

    public void setNotificacionesCollection1(Collection<Notificaciones> notificacionesCollection1) {
        this.notificacionesCollection1 = notificacionesCollection1;
    }

    public Collection<Cuadricula> getCuadriculaCollection() {
        return cuadriculaCollection;
    }

    public void setCuadriculaCollection(Collection<Cuadricula> cuadriculaCollection) {
        this.cuadriculaCollection = cuadriculaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuario[nif=" + nif + "]";
    }

}
