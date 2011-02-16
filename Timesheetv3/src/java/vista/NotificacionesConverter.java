/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.NotificacionesJpaController;
import modelo.Notificaciones;

/**
 *
 * @author Francisco
 */
public class NotificacionesConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        NotificacionesJpaController controller = (NotificacionesJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "notificacionesJpa");
        return controller.findNotificaciones(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Notificaciones) {
            Notificaciones o = (Notificaciones) object;
            return o.getIdNotificaciones() == null ? "" : o.getIdNotificaciones().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.Notificaciones");
        }
    }

}
