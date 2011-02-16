/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "NotificacionPendiente", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "NotificacionPendiente.findAll", query = "SELECT n FROM NotificacionPendiente n")})
public class NotificacionPendiente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idNotificaciones")
    private Integer idNotificaciones;
    @Basic(optional = false)
    @Column(name = "plazo")
    @Temporal(TemporalType.DATE)
    private Date plazo;
    @JoinColumn(name = "idNotificaciones", referencedColumnName = "idNotificaciones", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Notificaciones notificaciones;

    public NotificacionPendiente() {
    }

    public NotificacionPendiente(Integer idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public NotificacionPendiente(Integer idNotificaciones, Date plazo) {
        this.idNotificaciones = idNotificaciones;
        this.plazo = plazo;
    }

    public Integer getIdNotificaciones() {
        return idNotificaciones;
    }

    public void setIdNotificaciones(Integer idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public Date getPlazo() {
        return plazo;
    }

    public void setPlazo(Date plazo) {
        this.plazo = plazo;
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
        if (!(object instanceof NotificacionPendiente)) {
            return false;
        }
        NotificacionPendiente other = (NotificacionPendiente) object;
        if ((this.idNotificaciones == null && other.idNotificaciones != null) || (this.idNotificaciones != null && !this.idNotificaciones.equals(other.idNotificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NotificacionPendiente[idNotificaciones=" + idNotificaciones + "]";
    }

}
