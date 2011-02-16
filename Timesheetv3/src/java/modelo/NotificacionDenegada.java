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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "NotificacionDenegada", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "NotificacionDenegada.findAll", query = "SELECT n FROM NotificacionDenegada n")})
public class NotificacionDenegada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idNotificaciones")
    private Integer idNotificaciones;
    @Lob
    @Column(name = "motivo")
    private String motivo;
    @JoinColumn(name = "idNotificaciones", referencedColumnName = "idNotificaciones", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Notificaciones notificaciones;

    public NotificacionDenegada() {
    }

    public NotificacionDenegada(Integer idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public Integer getIdNotificaciones() {
        return idNotificaciones;
    }

    public void setIdNotificaciones(Integer idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Notificaciones getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Notificaciones notificaciones) {
        this.notificaciones = notificaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificaciones != null ? idNotificaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificacionDenegada)) {
            return false;
        }
        NotificacionDenegada other = (NotificacionDenegada) object;
        if ((this.idNotificaciones == null && other.idNotificaciones != null) || (this.idNotificaciones != null && !this.idNotificaciones.equals(other.idNotificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NotificacionDenegada[idNotificaciones=" + idNotificaciones + "]";
    }

}
