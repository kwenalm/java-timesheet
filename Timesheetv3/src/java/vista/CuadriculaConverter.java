/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.CuadriculaJpaController;
import modelo.Cuadricula;

/**
 *
 * @author Francisco
 */
public class CuadriculaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        CuadriculaJpaController controller = (CuadriculaJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "cuadriculaJpa");
        return controller.findCuadricula(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Cuadricula) {
            Cuadricula o = (Cuadricula) object;
            return o.getIdCuadricula() == null ? "" : o.getIdCuadricula().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.Cuadricula");
        }
    }

}
