package modelo;
// Generated 16-feb-2011 10:22:20 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cuadricula generated by hbm2java
 */
public class Cuadricula  implements java.io.Serializable {


     private Integer idCuadricula;
     private Usuario usuario;
     private Date fechaInicio;
     private String estado;
     private Set notificacioneses = new HashSet(0);
     private Set datoCuadriculas = new HashSet(0);

    public Cuadricula() {
    }

	
    public Cuadricula(Usuario usuario, Date fechaInicio) {
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
    }
    public Cuadricula(Usuario usuario, Date fechaInicio, String estado, Set notificacioneses, Set datoCuadriculas) {
       this.usuario = usuario;
       this.fechaInicio = fechaInicio;
       this.estado = estado;
       this.notificacioneses = notificacioneses;
       this.datoCuadriculas = datoCuadriculas;
    }
   
    public Integer getIdCuadricula() {
        return this.idCuadricula;
    }
    
    public void setIdCuadricula(Integer idCuadricula) {
        this.idCuadricula = idCuadricula;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getNotificacioneses() {
        return this.notificacioneses;
    }
    
    public void setNotificacioneses(Set notificacioneses) {
        this.notificacioneses = notificacioneses;
    }
    public Set getDatoCuadriculas() {
        return this.datoCuadriculas;
    }
    
    public void setDatoCuadriculas(Set datoCuadriculas) {
        this.datoCuadriculas = datoCuadriculas;
    }




}

