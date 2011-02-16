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
import jpa.NotificacionesJpaController;
import modelo.Notificaciones;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.IllegalOrphanException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class NotificacionesController {

    public NotificacionesController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (NotificacionesJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "notificacionesJpa");
        pagingInfo = new PagingInfo();
        converter = new NotificacionesConverter();
    }
    private Notificaciones notificaciones = null;
    private List<Notificaciones> notificacionesItems = null;
    private NotificacionesJpaController jpaController = null;
    private NotificacionesConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getNotificacionesCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getNotificacionesItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findNotificacionesEntities(), false);
    }

    public SelectItem[] getNotificacionesItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findNotificacionesEntities(), true);
    }

    public Notificaciones getNotificaciones() {
        if (notificaciones == null) {
            notificaciones = (Notificaciones) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotificaciones", converter, null);
        }
        if (notificaciones == null) {
            notificaciones = new Notificaciones();
        }
        return notificaciones;
    }

    public String listSetup() {
        reset(true);
        return "notificaciones_list";
    }

    public String createSetup() {
        reset(false);
        notificaciones = new Notificaciones();
        return "notificaciones_create";
    }

    public String create() {
        try {
            jpaController.create(notificaciones);
            JsfUtil.addSuccessMessage("Notificaciones was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("notificaciones_detail");
    }

    public String editSetup() {
        return scalarSetup("notificaciones_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        notificaciones = (Notificaciones) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotificaciones", converter, null);
        if (notificaciones == null) {
            String requestNotificacionesString = JsfUtil.getRequestParameter("jsfcrud.currentNotificaciones");
            JsfUtil.addErrorMessage("The notificaciones with id " + requestNotificacionesString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String notificacionesString = converter.getAsString(FacesContext.getCurrentInstance(), null, notificaciones);
        String currentNotificacionesString = JsfUtil.getRequestParameter("jsfcrud.currentNotificaciones");
        if (notificacionesString == null || notificacionesString.length() == 0 || !notificacionesString.equals(currentNotificacionesString)) {
            String outcome = editSetup();
            if ("notificaciones_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit notificaciones. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(notificaciones);
            JsfUtil.addSuccessMessage("Notificaciones was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentNotificaciones");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Notificaciones was successfully deleted.");
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

    public List<Notificaciones> getNotificacionesItems() {
        if (notificacionesItems == null) {
            getPagingInfo();
            notificacionesItems = jpaController.findNotificacionesEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return notificacionesItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "notificaciones_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "notificaciones_list";
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
        notificaciones = null;
        notificacionesItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Notificaciones newNotificaciones = new Notificaciones();
        String newNotificacionesString = converter.getAsString(FacesContext.getCurrentInstance(), null, newNotificaciones);
        String notificacionesString = converter.getAsString(FacesContext.getCurrentInstance(), null, notificaciones);
        if (!newNotificacionesString.equals(notificacionesString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
