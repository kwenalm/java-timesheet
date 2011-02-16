/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.AutentificacionJpaController;
import modelo.Autentificacion;

/**
 *
 * @author Francisco
 */
public class AutentificacionConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        String id = string;
        AutentificacionJpaController controller = (AutentificacionJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "autentificacionJpa");
        return controller.findAutentificacion(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Autentificacion) {
            Autentificacion o = (Autentificacion) object;
            return o.getNif() == null ? "" : o.getNif().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.Autentificacion");
        }
    }

}
