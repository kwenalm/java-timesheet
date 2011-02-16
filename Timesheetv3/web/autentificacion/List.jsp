<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Autentificacion Items</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Autentificacion Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Autentificacion Items Found)<br />" rendered="#{autentificacion.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{autentificacion.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{autentificacion.pagingInfo.firstItem + 1}..#{autentificacion.pagingInfo.lastItem} of #{autentificacion.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{autentificacion.prev}" value="Previous #{autentificacion.pagingInfo.batchSize}" rendered="#{autentificacion.pagingInfo.firstItem >= autentificacion.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{autentificacion.next}" value="Next #{autentificacion.pagingInfo.batchSize}" rendered="#{autentificacion.pagingInfo.lastItem + autentificacion.pagingInfo.batchSize <= autentificacion.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{autentificacion.next}" value="Remaining #{autentificacion.pagingInfo.itemCount - autentificacion.pagingInfo.lastItem}"
                               rendered="#{autentificacion.pagingInfo.lastItem < autentificacion.pagingInfo.itemCount && autentificacion.pagingInfo.lastItem + autentificacion.pagingInfo.batchSize > autentificacion.pagingInfo.itemCount}"/>
                <h:dataTable value="#{autentificacion.autentificacionItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nif"/>
                        </f:facet>
                        <h:outputText value="#{item.nif}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Clave"/>
                        </f:facet>
                        <h:outputText value="#{item.clave}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Usuario"/>
                        </f:facet>
                        <h:outputText value="#{item.usuario}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{autentificacion.detailSetup}">
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][autentificacion.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{autentificacion.editSetup}">
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][autentificacion.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{autentificacion.destroy}">
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][autentificacion.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{autentificacion.createSetup}" value="New Autentificacion"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
