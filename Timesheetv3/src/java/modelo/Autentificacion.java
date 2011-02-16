/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "Autentificacion", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Autentificacion.findAll", query = "SELECT a FROM Autentificacion a")})
public class Autentificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nif")
    private String nif;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @JoinColumn(name = "nif", referencedColumnName = "nif", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Autentificacion() {
    }

    public Autentificacion(String nif) {
        this.nif = nif;
    }

    public Autentificacion(String nif, String clave) {
        this.nif = nif;
        this.clave = clave;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Autentificacion)) {
            return false;
        }
        Autentificacion other = (Autentificacion) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autentificacion[nif=" + nif + "]";
    }

}
