package modelo;

import java.util.Date;

public class DatoCuadricula  implements java.io.Serializable {

     private int idDatoCuadricula;
    // private Cuadricula cuadricula;
     private Date dia;
     private int horas;
     private String dpto;

    public DatoCuadricula() {
    }

    public DatoCuadricula(int idDatoCuadricula, Cuadricula cuadricula, Date dia, int horas, String dpto) {
       this.idDatoCuadricula = idDatoCuadricula;
     //  this.cuadricula = cuadricula;
       this.dia = dia;
       this.horas = horas;
       this.dpto = dpto;
    }
   
    public int getIdDatoCuadricula() {
        return this.idDatoCuadricula;
    }
    
    public void setIdDatoCuadricula(int idDatoCuadricula) {
        this.idDatoCuadricula = idDatoCuadricula;
    }
    /*
    public Cuadricula getCuadricula() {
        return this.cuadricula;
    }
    
    public void setCuadricula(Cuadricula cuadricula) {
        this.cuadricula = cuadricula;
    }
     
     */
    public Date getDia() {
        return this.dia;
    }
    
    public void setDia(Date dia) {
        this.dia = dia;
    }
    public int getHoras() {
        return this.horas;
    }
    
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public String getDpto() {
        return this.dpto;
    }
    
    public void setDpto(String dpto) {
        this.dpto = dpto;
    }
}


