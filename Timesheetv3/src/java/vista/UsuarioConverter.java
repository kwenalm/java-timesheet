/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.UsuarioJpaController;
import modelo.Usuario;

/**
 *
 * @author Francisco
 */
public class UsuarioConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        String id = string;
        UsuarioJpaController controller = (UsuarioJpaController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "usuarioJpa");
        return controller.findUsuario(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Usuario) {
            Usuario o = (Usuario) object;
            return o.getNif() == null ? "" : o.getNif().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.Usuario");
        }
    }

}
