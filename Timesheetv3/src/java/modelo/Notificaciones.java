/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Notificaciones", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n")})
public class Notificaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idNotificaciones")
    private Integer idNotificaciones;
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "notificaciones")
    private NotificacionDenegada notificacionDenegada;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "notificaciones")
    private NotificacionPendiente notificacionPendiente;
    @JoinColumn(name = "idCuadricula", referencedColumnName = "idCuadricula")
    @ManyToOne(optional = false)
    private Cuadricula idCuadricula;
    @JoinColumn(name = "remitente", referencedColumnName = "nif")
    @ManyToOne(optional = false)
    private Usuario remitente;
    @JoinColumn(name = "destinatario", referencedColumnName = "nif")
    @ManyToOne(optional = false)
    private Usuario destinatario;

    public Notificaciones() {
    }

    public Notificaciones(Integer idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public Notificaciones(Integer idNotificaciones, Date fecha) {
        this.idNotificaciones = idNotificaciones;
        this.fecha = fecha;
    }

    public Integer getIdNotificaciones() {
        return idNotificaciones;
    }

    public void setIdNotificaciones(Integer idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public NotificacionDenegada getNotificacionDenegada() {
        return notificacionDenegada;
    }

    public void setNotificacionDenegada(NotificacionDenegada notificacionDenegada) {
        this.notificacionDenegada = notificacionDenegada;
    }

    public NotificacionPendiente getNotificacionPendiente() {
        return notificacionPendiente;
    }

    public void setNotificacionPendiente(NotificacionPendiente notificacionPendiente) {
        this.notificacionPendiente = notificacionPendiente;
    }

    public Cuadricula getIdCuadricula() {
        return idCuadricula;
    }

    public void setIdCuadricula(Cuadricula idCuadricula) {
        this.idCuadricula = idCuadricula;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
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
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        if ((this.idNotificaciones == null && other.idNotificaciones != null) || (this.idNotificaciones != null && !this.idNotificaciones.equals(other.idNotificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Notificaciones[idNotificaciones=" + idNotificaciones + "]";
    }

}
