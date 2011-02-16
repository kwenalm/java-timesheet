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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "DatoCuadricula", catalog = "lacue1_grupo2", schema = "")
@NamedQueries({
    @NamedQuery(name = "DatoCuadricula.findAll", query = "SELECT d FROM DatoCuadricula d")})
public class DatoCuadricula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDatoCuadricula")
    private Integer idDatoCuadricula;
    @Basic(optional = false)
    @Column(name = "dia")
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Basic(optional = false)
    @Column(name = "horas")
    private int horas;
    @Basic(optional = false)
    @Column(name = "dpto")
    private String dpto;
    @JoinColumn(name = "idCuadricula", referencedColumnName = "idCuadricula")
    @ManyToOne(optional = false)
    private Cuadricula idCuadricula;

    public DatoCuadricula() {
    }

    public DatoCuadricula(Integer idDatoCuadricula) {
        this.idDatoCuadricula = idDatoCuadricula;
    }

    public DatoCuadricula(Integer idDatoCuadricula, Date dia, int horas, String dpto) {
        this.idDatoCuadricula = idDatoCuadricula;
        this.dia = dia;
        this.horas = horas;
        this.dpto = dpto;
    }

    public Integer getIdDatoCuadricula() {
        return idDatoCuadricula;
    }

    public void setIdDatoCuadricula(Integer idDatoCuadricula) {
        this.idDatoCuadricula = idDatoCuadricula;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public Cuadricula getIdCuadricula() {
        return idCuadricula;
    }

    public void setIdCuadricula(Cuadricula idCuadricula) {
        this.idCuadricula = idCuadricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatoCuadricula != null ? idDatoCuadricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoCuadricula)) {
            return false;
        }
        DatoCuadricula other = (DatoCuadricula) object;
        if ((this.idDatoCuadricula == null && other.idDatoCuadricula != null) || (this.idDatoCuadricula != null && !this.idDatoCuadricula.equals(other.idDatoCuadricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DatoCuadricula[idDatoCuadricula=" + idDatoCuadricula + "]";
    }

}
