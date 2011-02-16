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
import jpa.DatoCuadriculaJpaController;
import modelo.DatoCuadricula;
import vista.util.JsfUtil;
import jpa.exceptions.NonexistentEntityException;
import vista.util.PagingInfo;

/**
 *
 * @author Francisco
 */
public class DatoCuadriculaController {

    public DatoCuadriculaController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (DatoCuadriculaJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "datoCuadriculaJpa");
        pagingInfo = new PagingInfo();
        converter = new DatoCuadriculaConverter();
    }
    private DatoCuadricula datoCuadricula = null;
    private List<DatoCuadricula> datoCuadriculaItems = null;
    private DatoCuadriculaJpaController jpaController = null;
    private DatoCuadriculaConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getDatoCuadriculaCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getDatoCuadriculaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findDatoCuadriculaEntities(), false);
    }

    public SelectItem[] getDatoCuadriculaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findDatoCuadriculaEntities(), true);
    }

    public DatoCuadricula getDatoCuadricula() {
        if (datoCuadricula == null) {
            datoCuadricula = (DatoCuadricula) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDatoCuadricula", converter, null);
        }
        if (datoCuadricula == null) {
            datoCuadricula = new DatoCuadricula();
        }
        return datoCuadricula;
    }

    public String listSetup() {
        reset(true);
        return "datoCuadricula_list";
    }

    public String createSetup() {
        reset(false);
        datoCuadricula = new DatoCuadricula();
        return "datoCuadricula_create";
    }

    public String create() {
        try {
            jpaController.create(datoCuadricula);
            JsfUtil.addSuccessMessage("DatoCuadricula was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("datoCuadricula_detail");
    }

    public String editSetup() {
        return scalarSetup("datoCuadricula_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        datoCuadricula = (DatoCuadricula) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDatoCuadricula", converter, null);
        if (datoCuadricula == null) {
            String requestDatoCuadriculaString = JsfUtil.getRequestParameter("jsfcrud.currentDatoCuadricula");
            JsfUtil.addErrorMessage("The datoCuadricula with id " + requestDatoCuadriculaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String datoCuadriculaString = converter.getAsString(FacesContext.getCurrentInstance(), null, datoCuadricula);
        String currentDatoCuadriculaString = JsfUtil.getRequestParameter("jsfcrud.currentDatoCuadricula");
        if (datoCuadriculaString == null || datoCuadriculaString.length() == 0 || !datoCuadriculaString.equals(currentDatoCuadriculaString)) {
            String outcome = editSetup();
            if ("datoCuadricula_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit datoCuadricula. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(datoCuadricula);
            JsfUtil.addSuccessMessage("DatoCuadricula was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDatoCuadricula");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("DatoCuadricula was successfully deleted.");
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

    public List<DatoCuadricula> getDatoCuadriculaItems() {
        if (datoCuadriculaItems == null) {
            getPagingInfo();
            datoCuadriculaItems = jpaController.findDatoCuadriculaEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return datoCuadriculaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "datoCuadricula_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "datoCuadricula_list";
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
        datoCuadricula = null;
        datoCuadriculaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        DatoCuadricula newDatoCuadricula = new DatoCuadricula();
        String newDatoCuadriculaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDatoCuadricula);
        String datoCuadriculaString = converter.getAsString(FacesContext.getCurrentInstance(), null, datoCuadricula);
        if (!newDatoCuadriculaString.equals(datoCuadriculaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
