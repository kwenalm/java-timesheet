package modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Cuadricula  implements java.io.Serializable {

     private Integer idCuadricula;
     private Usuario usuario;
     private Date fechaInicio;
     private String estado;  // Enum: Pendiente, Enviada, Aprobada, Denegada
    // private Set notificacioneses = new HashSet(0);
     private Set<DatoCuadricula> datosCuadricula;

    public Cuadricula() {
        
    }

/*
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
  */
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
    /*
    public Set getNotificacioneses() {
        return this.notificacioneses;
    }
    
    public void setNotificacioneses(Set notificacioneses) {
        this.notificacioneses = notificacioneses;
    }
     */

    public Set<DatoCuadricula> getDatosCuadricula() {
        return this.datosCuadricula;
    }
    
    public void setDatosCuadricula(Set<DatoCuadricula> datoCuadriculas) {
        this.datosCuadricula = datoCuadriculas;
    }
}


