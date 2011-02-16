/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.DatoCuadriculaJpaController;
import modelo.DatoCuadricula;

/**
 *
 * @author Francisco
 */
public class DatoCuadriculaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        DatoCuadriculaJpaController controller = (DatoCuadriculaJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "datoCuadriculaJpa");
        return controller.findDatoCuadricula(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof DatoCuadricula) {
            DatoCuadricula o = (DatoCuadricula) object;
            return o.getIdDatoCuadricula() == null ? "" : o.getIdDatoCuadricula().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.DatoCuadricula");
        }
    }

}
