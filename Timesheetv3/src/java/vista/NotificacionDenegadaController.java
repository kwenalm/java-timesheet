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
import jpa.NotificacionDenegadaJpaController;
import modelo.NotificacionDenegada;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.IllegalOrphanException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class NotificacionDenegadaController {

    public NotificacionDenegadaController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (NotificacionDenegadaJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "notificacionDenegadaJpa");
        pagingInfo = new PagingInfo();
        converter = new NotificacionDenegadaConverter();
    }
    private NotificacionDenegada notificacionDenegada = null;
    private List<NotificacionDenegada> notificacionDenegadaItems = null;
    private NotificacionDenegadaJpaController jpaController = null;
    private NotificacionDenegadaConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getNotificacionDenegadaCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getNotificacionDenegadaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findNotificacionDenegadaEntities(), false);
    }

    public SelectItem[] getNotificacionDenegadaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findNotificacionDenegadaEntities(), true);
    }

    public NotificacionDenegada getNotificacionDenegada() {
        if (notificacionDenegada == null) {
            notificacionDenegada = (NotificacionDenegada) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotificacionDenegada", converter, null);
        }
        if (notificacionDenegada == null) {
            notificacionDenegada = new NotificacionDenegada();
        }
        return notificacionDenegada;
    }

    public String listSetup() {
        reset(true);
        return "notificacionDenegada_list";
    }

    public String createSetup() {
        reset(false);
        notificacionDenegada = new NotificacionDenegada();
        return "notificacionDenegada_create";
    }

    public String create() {
        try {
            jpaController.create(notificacionDenegada);
            JsfUtil.addSuccessMessage("NotificacionDenegada was successfully created.");
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
        return scalarSetup("notificacionDenegada_detail");
    }

    public String editSetup() {
        return scalarSetup("notificacionDenegada_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        notificacionDenegada = (NotificacionDenegada) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotificacionDenegada", converter, null);
        if (notificacionDenegada == null) {
            String requestNotificacionDenegadaString = JsfUtil.getRequestParameter("jsfcrud.currentNotificacionDenegada");
            JsfUtil.addErrorMessage("The notificacionDenegada with id " + requestNotificacionDenegadaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String notificacionDenegadaString = converter.getAsString(FacesContext.getCurrentInstance(), null, notificacionDenegada);
        String currentNotificacionDenegadaString = JsfUtil.getRequestParameter("jsfcrud.currentNotificacionDenegada");
        if (notificacionDenegadaString == null || notificacionDenegadaString.length() == 0 || !notificacionDenegadaString.equals(currentNotificacionDenegadaString)) {
            String outcome = editSetup();
            if ("notificacionDenegada_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit notificacionDenegada. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(notificacionDenegada);
            JsfUtil.addSuccessMessage("NotificacionDenegada was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentNotificacionDenegada");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("NotificacionDenegada was successfully deleted.");
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

    public List<NotificacionDenegada> getNotificacionDenegadaItems() {
        if (notificacionDenegadaItems == null) {
            getPagingInfo();
            notificacionDenegadaItems = jpaController.findNotificacionDenegadaEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return notificacionDenegadaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "notificacionDenegada_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "notificacionDenegada_list";
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
        notificacionDenegada = null;
        notificacionDenegadaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        NotificacionDenegada newNotificacionDenegada = new NotificacionDenegada();
        String newNotificacionDenegadaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newNotificacionDenegada);
        String notificacionDenegadaString = converter.getAsString(FacesContext.getCurrentInstance(), null, notificacionDenegada);
        if (!newNotificacionDenegadaString.equals(notificacionDenegadaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
