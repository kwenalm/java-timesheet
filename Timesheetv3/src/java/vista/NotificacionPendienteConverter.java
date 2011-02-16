/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.NotificacionPendienteJpaController;
import modelo.NotificacionPendiente;

/**
 *
 * @author Francisco
 */
public class NotificacionPendienteConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        NotificacionPendienteJpaController controller = (NotificacionPendienteJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "notificacionPendienteJpa");
        return controller.findNotificacionPendiente(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof NotificacionPendiente) {
            NotificacionPendiente o = (NotificacionPendiente) object;
            return o.getIdNotificaciones() == null ? "" : o.getIdNotificaciones().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.NotificacionPendiente");
        }
    }

}
