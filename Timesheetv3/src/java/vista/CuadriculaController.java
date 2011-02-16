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
import jpa.CuadriculaJpaController;
import modelo.Cuadricula;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.IllegalOrphanException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class CuadriculaController {

    public CuadriculaController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CuadriculaJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cuadriculaJpa");
        pagingInfo = new PagingInfo();
        converter = new CuadriculaConverter();
    }
    private Cuadricula cuadricula = null;
    private List<Cuadricula> cuadriculaItems = null;
    private CuadriculaJpaController jpaController = null;
    private CuadriculaConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCuadriculaCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getCuadriculaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCuadriculaEntities(), false);
    }

    public SelectItem[] getCuadriculaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCuadriculaEntities(), true);
    }

    public Cuadricula getCuadricula() {
        if (cuadricula == null) {
            cuadricula = (Cuadricula) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCuadricula", converter, null);
        }
        if (cuadricula == null) {
            cuadricula = new Cuadricula();
        }
        return cuadricula;
    }

    public String listSetup() {
        reset(true);
        return "cuadricula_list";
    }

    public String createSetup() {
        reset(false);
        cuadricula = new Cuadricula();
        return "cuadricula_create";
    }

    public String create() {
        try {
            jpaController.create(cuadricula);
            JsfUtil.addSuccessMessage("Cuadricula was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("cuadricula_detail");
    }

    public String editSetup() {
        return scalarSetup("cuadricula_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cuadricula = (Cuadricula) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCuadricula", converter, null);
        if (cuadricula == null) {
            String requestCuadriculaString = JsfUtil.getRequestParameter("jsfcrud.currentCuadricula");
            JsfUtil.addErrorMessage("The cuadricula with id " + requestCuadriculaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cuadriculaString = converter.getAsString(FacesContext.getCurrentInstance(), null, cuadricula);
        String currentCuadriculaString = JsfUtil.getRequestParameter("jsfcrud.currentCuadricula");
        if (cuadriculaString == null || cuadriculaString.length() == 0 || !cuadriculaString.equals(currentCuadriculaString)) {
            String outcome = editSetup();
            if ("cuadricula_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cuadricula. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(cuadricula);
            JsfUtil.addSuccessMessage("Cuadricula was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCuadricula");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Cuadricula was successfully deleted.");
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

    public List<Cuadricula> getCuadriculaItems() {
        if (cuadriculaItems == null) {
            getPagingInfo();
            cuadriculaItems = jpaController.findCuadriculaEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return cuadriculaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cuadricula_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cuadricula_list";
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
        cuadricula = null;
        cuadriculaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Cuadricula newCuadricula = new Cuadricula();
        String newCuadriculaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCuadricula);
        String cuadriculaString = converter.getAsString(FacesContext.getCurrentInstance(), null, cuadricula);
        if (!newCuadriculaString.equals(cuadriculaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
