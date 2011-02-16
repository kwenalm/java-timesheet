/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import jpa.UsuarioJpaController;
import modelo.Usuario;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.IllegalOrphanException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class UsuarioController {

    public UsuarioController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (UsuarioJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "usuarioJpa");
        pagingInfo = new PagingInfo();
        converter = new UsuarioConverter();
    }
    private Usuario usuario = null;
    private List<Usuario> usuarioItems = null;
    private UsuarioJpaController jpaController = null;
    private UsuarioConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getUsuarioCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getUsuarioItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findUsuarioEntities(), false);
    }

    public SelectItem[] getUsuarioItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findUsuarioEntities(), true);
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = (Usuario) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUsuario", converter, null);
        }
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public String listSetup() {
        reset(true);
        return "usuario_list";
    }

    public String createSetup() {
        reset(false);
        usuario = new Usuario();
        return "usuario_create";
    }

    public String create() {
        try {
            jpaController.create(usuario);
            JsfUtil.addSuccessMessage("Usuario was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("usuario_detail");
    }

    public String editSetup() {
        return scalarSetup("usuario_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        usuario = (Usuario) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUsuario", converter, null);
        if (usuario == null) {
            String requestUsuarioString = JsfUtil.getRequestParameter("jsfcrud.currentUsuario");
            JsfUtil.addErrorMessage("The usuario with id " + requestUsuarioString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String usuarioString = converter.getAsString(FacesContext.getCurrentInstance(), null, usuario);
        String currentUsuarioString = JsfUtil.getRequestParameter("jsfcrud.currentUsuario");
        if (usuarioString == null || usuarioString.length() == 0 || !usuarioString.equals(currentUsuarioString)) {
            String outcome = editSetup();
            if ("usuario_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit usuario. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(usuario);
            JsfUtil.addSuccessMessage("Usuario was successfully updated.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return listSetup();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String destroy() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentUsuario");
        String id = idAsString;
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Usuario was successfully deleted.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return relatedOrListOutcome();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Usuario> getUsuarioItems() {
        if (usuarioItems == null) {
            getPagingInfo();
            usuarioItems = jpaController.findUsuarioEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return usuarioItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "usuario_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "usuario_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        usuario = null;
        usuarioItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Usuario newUsuario = new Usuario();
        String newUsuarioString = converter.getAsString(FacesContext.getCurrentInstance(), null, newUsuario);
        String usuarioString = converter.getAsString(FacesContext.getCurrentInstance(), null, usuario);
        if (!newUsuarioString.equals(usuarioString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
