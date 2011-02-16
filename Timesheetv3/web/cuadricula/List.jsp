<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Cuadricula Items</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Cuadricula Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Cuadricula Items Found)<br />" rendered="#{cuadricula.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{cuadricula.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{cuadricula.pagingInfo.firstItem + 1}..#{cuadricula.pagingInfo.lastItem} of #{cuadricula.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{cuadricula.prev}" value="Previous #{cuadricula.pagingInfo.batchSize}" rendered="#{cuadricula.pagingInfo.firstItem >= cuadricula.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{cuadricula.next}" value="Next #{cuadricula.pagingInfo.batchSize}" rendered="#{cuadricula.pagingInfo.lastItem + cuadricula.pagingInfo.batchSize <= cuadricula.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{cuadricula.next}" value="Remaining #{cuadricula.pagingInfo.itemCount - cuadricula.pagingInfo.lastItem}"
                               rendered="#{cuadricula.pagingInfo.lastItem < cuadricula.pagingInfo.itemCount && cuadricula.pagingInfo.lastItem + cuadricula.pagingInfo.batchSize > cuadricula.pagingInfo.itemCount}"/>
                <h:dataTable value="#{cuadricula.cuadriculaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdCuadricula"/>
                        </f:facet>
                        <h:outputText value="#{item.idCuadricula}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="FechaInicio"/>
                        </f:facet>
                        <h:outputText value="#{item.fechaInicio}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Estado"/>
                        </f:facet>
                        <h:outputText value="#{item.estado}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nif"/>
                        </f:facet>
                        <h:outputText value="#{item.nif}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{cuadricula.detailSetup}">
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cuadricula.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{cuadricula.editSetup}">
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cuadricula.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{cuadricula.destroy}">
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cuadricula.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{cuadricula.createSetup}" value="New Cuadricula"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
