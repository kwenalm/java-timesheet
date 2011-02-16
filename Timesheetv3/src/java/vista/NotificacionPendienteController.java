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
import jpa.NotificacionPendienteJpaController;
import modelo.NotificacionPendiente;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.IllegalOrphanException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class NotificacionPendienteController {

    public NotificacionPendienteController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (NotificacionPendienteJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "notificacionPendienteJpa");
        pagingInfo = new PagingInfo();
        converter = new NotificacionPendienteConverter();
    }
    private NotificacionPendiente notificacionPendiente = null;
    private List<NotificacionPendiente> notificacionPendienteItems = null;
    private NotificacionPendienteJpaController jpaController = null;
    private NotificacionPendienteConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getNotificacionPendienteCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getNotificacionPendienteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findNotificacionPendienteEntities(), false);
    }

    public SelectItem[] getNotificacionPendienteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findNotificacionPendienteEntities(), true);
    }

    public NotificacionPendiente getNotificacionPendiente() {
        if (notificacionPendiente == null) {
            notificacionPendiente = (NotificacionPendiente) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotificacionPendiente", converter, null);
        }
        if (notificacionPendiente == null) {
            notificacionPendiente = new NotificacionPendiente();
        }
        return notificacionPendiente;
    }

    public String listSetup() {
        reset(true);
        return "notificacionPendiente_list";
    }

    public String createSetup() {
        reset(false);
        notificacionPendiente = new NotificacionPendiente();
        return "notificacionPendiente_create";
    }

    public String create() {
        try {
            jpaController.create(notificacionPendiente);
            JsfUtil.addSuccessMessage("NotificacionPendiente was successfully created.");
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
        return scalarSetup("notificacionPendiente_detail");
    }

    public String editSetup() {
        return scalarSetup("notificacionPendiente_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        notificacionPendiente = (NotificacionPendiente) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotificacionPendiente", converter, null);
        if (notificacionPendiente == null) {
            String requestNotificacionPendienteString = JsfUtil.getRequestParameter("jsfcrud.currentNotificacionPendiente");
            JsfUtil.addErrorMessage("The notificacionPendiente with id " + requestNotificacionPendienteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String notificacionPendienteString = converter.getAsString(FacesContext.getCurrentInstance(), null, notificacionPendiente);
        String currentNotificacionPendienteString = JsfUtil.getRequestParameter("jsfcrud.currentNotificacionPendiente");
        if (notificacionPendienteString == null || notificacionPendienteString.length() == 0 || !notificacionPendienteString.equals(currentNotificacionPendienteString)) {
            String outcome = editSetup();
            if ("notificacionPendiente_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit notificacionPendiente. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(notificacionPendiente);
            JsfUtil.addSuccessMessage("NotificacionPendiente was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentNotificacionPendiente");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("NotificacionPendiente was successfully deleted.");
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

    public List<NotificacionPendiente> getNotificacionPendienteItems() {
        if (notificacionPendienteItems == null) {
            getPagingInfo();
            notificacionPendienteItems = jpaController.findNotificacionPendienteEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return notificacionPendienteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "notificacionPendiente_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "notificacionPendiente_list";
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
        notificacionPendiente = null;
        notificacionPendienteItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        NotificacionPendiente newNotificacionPendiente = new NotificacionPendiente();
        String newNotificacionPendienteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newNotificacionPendiente);
        String notificacionPendienteString = converter.getAsString(FacesContext.getCurrentInstance(), null, notificacionPendiente);
        if (!newNotificacionPendienteString.equals(notificacionPendienteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
