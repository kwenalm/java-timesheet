/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Java I
 */
public class manejaInputs{
   private boolean verNombreNif;
   private boolean verListaDptos;
   private String cadena;
   private String cadena2;

    public String getCadena2() {
        return cadena2;
    }

    public void setCadena2(String cadena2) {
        this.cadena2 = cadena2;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public manejaInputs() {
    }
    public boolean isVerListaDptos() {
        return verListaDptos;
    }

    public void setVerListaDptos(boolean verListaDptos) {
        this.verListaDptos = verListaDptos;
    }

    public boolean isVerNombreNif() {
        return verNombreNif;
    }

    public void setVerNombreNif(boolean verNombreNif) {
        this.verNombreNif = verNombreNif;
    }

    public void pepe(ValueChangeEvent e){
        cadena2="enelevento";
        if(cadena.equalsIgnoreCase("nif")||cadena.equalsIgnoreCase("nombre")){
           cadena2="entraIf";
           verNombreNif=true;
           verListaDptos=false;
        }
    else{
        cadena2="entraElse";
        verListaDptos=true;
        verNombreNif=false;
        }
        FacesContext context=FacesContext.getCurrentInstance();
        UIComponent vista=(UIComponent)context.getViewRoot();
        vista.setRendered(true);
    }
    
    public void cambiarVisibilidad(ValueChangeEvent vce) {
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("radios").toString();
        verNombreNif=true;
    }
}
