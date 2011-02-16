/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "Cuadricula", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cuadricula.findAll", query = "SELECT c FROM Cuadricula c")})
public class Cuadricula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCuadricula")
    private Integer idCuadricula;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuadricula")
    private Collection<Notificaciones> notificacionesCollection;
    @JoinColumn(name = "nif", referencedColumnName = "nif")
    @ManyToOne(optional = false)
    private Usuario nif;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuadricula")
    private Collection<DatoCuadricula> datoCuadriculaCollection;

    public Cuadricula() {
    }

    public Cuadricula(Integer idCuadricula) {
        this.idCuadricula = idCuadricula;
    }

    public Cuadricula(Integer idCuadricula, Date fechaInicio) {
        this.idCuadricula = idCuadricula;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdCuadricula() {
        return idCuadricula;
    }

    public void setIdCuadricula(Integer idCuadricula) {
        this.idCuadricula = idCuadricula;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Notificaciones> getNotificacionesCollection() {
        return notificacionesCollection;
    }

    public void setNotificacionesCollection(Collection<Notificaciones> notificacionesCollection) {
        this.notificacionesCollection = notificacionesCollection;
    }

    public Usuario getNif() {
        return nif;
    }

    public void setNif(Usuario nif) {
        this.nif = nif;
    }

    public Collection<DatoCuadricula> getDatoCuadriculaCollection() {
        return datoCuadriculaCollection;
    }

    public void setDatoCuadriculaCollection(Collection<DatoCuadricula> datoCuadriculaCollection) {
        this.datoCuadriculaCollection = datoCuadriculaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuadricula != null ? idCuadricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuadricula)) {
            return false;
        }
        Cuadricula other = (Cuadricula) object;
        if ((this.idCuadricula == null && other.idCuadricula != null) || (this.idCuadricula != null && !this.idCuadricula.equals(other.idCuadricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cuadricula[idCuadricula=" + idCuadricula + "]";
    }

}
