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
import jpa.AutentificacionJpaController;
import modelo.Autentificacion;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.IllegalOrphanException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class AutentificacionController {

    public AutentificacionController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (AutentificacionJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "autentificacionJpa");
        pagingInfo = new PagingInfo();
        converter = new AutentificacionConverter();
    }
    private Autentificacion autentificacion = null;
    private List<Autentificacion> autentificacionItems = null;
    private AutentificacionJpaController jpaController = null;
    private AutentificacionConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getAutentificacionCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getAutentificacionItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findAutentificacionEntities(), false);
    }

    public SelectItem[] getAutentificacionItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findAutentificacionEntities(), true);
    }

    public Autentificacion getAutentificacion() {
        if (autentificacion == null) {
            autentificacion = (Autentificacion) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAutentificacion", converter, null);
        }
        if (autentificacion == null) {
            autentificacion = new Autentificacion();
        }
        return autentificacion;
    }

    public String listSetup() {
        reset(true);
        return "autentificacion_list";
    }

    public String createSetup() {
        reset(false);
        autentificacion = new Autentificacion();
        return "autentificacion_create";
    }

    public String create() {
        try {
            jpaController.create(autentificacion);
            JsfUtil.addSuccessMessage("Autentificacion was successfully created.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("autentificacion_detail");
    }

    public String editSetup() {
        return scalarSetup("autentificacion_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        autentificacion = (Autentificacion) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAutentificacion", converter, null);
        if (autentificacion == null) {
            String requestAutentificacionString = JsfUtil.getRequestParameter("jsfcrud.currentAutentificacion");
            JsfUtil.addErrorMessage("The autentificacion with id " + requestAutentificacionString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String autentificacionString = converter.getAsString(FacesContext.getCurrentInstance(), null, autentificacion);
        String currentAutentificacionString = JsfUtil.getRequestParameter("jsfcrud.currentAutentificacion");
        if (autentificacionString == null || autentificacionString.length() == 0 || !autentificacionString.equals(currentAutentificacionString)) {
            String outcome = editSetup();
            if ("autentificacion_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit autentificacion. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(autentificacion);
            JsfUtil.addSuccessMessage("Autentificacion was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAutentificacion");
        String id = idAsString;
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Autentificacion was successfully deleted.");
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

    public List<Autentificacion> getAutentificacionItems() {
        if (autentificacionItems == null) {
            getPagingInfo();
            autentificacionItems = jpaController.findAutentificacionEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return autentificacionItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "autentificacion_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "autentificacion_list";
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
        autentificacion = null;
        autentificacionItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Autentificacion newAutentificacion = new Autentificacion();
        String newAutentificacionString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAutentificacion);
        String autentificacionString = converter.getAsString(FacesContext.getCurrentInstance(), null, autentificacion);
        if (!newAutentificacionString.equals(autentificacionString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
