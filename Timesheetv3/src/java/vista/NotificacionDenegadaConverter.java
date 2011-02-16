/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.NotificacionDenegadaJpaController;
import modelo.NotificacionDenegada;

/**
 *
 * @author Francisco
 */
public class NotificacionDenegadaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        NotificacionDenegadaJpaController controller = (NotificacionDenegadaJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "notificacionDenegadaJpa");
        return controller.findNotificacionDenegada(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof NotificacionDenegada) {
            NotificacionDenegada o = (NotificacionDenegada) object;
            return o.getIdNotificaciones() == null ? "" : o.getIdNotificaciones().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.NotificacionDenegada");
        }
    }

}
