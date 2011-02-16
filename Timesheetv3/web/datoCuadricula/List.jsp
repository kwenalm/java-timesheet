<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing DatoCuadricula Items</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing DatoCuadricula Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No DatoCuadricula Items Found)<br />" rendered="#{datoCuadricula.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{datoCuadricula.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{datoCuadricula.pagingInfo.firstItem + 1}..#{datoCuadricula.pagingInfo.lastItem} of #{datoCuadricula.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{datoCuadricula.prev}" value="Previous #{datoCuadricula.pagingInfo.batchSize}" rendered="#{datoCuadricula.pagingInfo.firstItem >= datoCuadricula.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{datoCuadricula.next}" value="Next #{datoCuadricula.pagingInfo.batchSize}" rendered="#{datoCuadricula.pagingInfo.lastItem + datoCuadricula.pagingInfo.batchSize <= datoCuadricula.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{datoCuadricula.next}" value="Remaining #{datoCuadricula.pagingInfo.itemCount - datoCuadricula.pagingInfo.lastItem}"
                               rendered="#{datoCuadricula.pagingInfo.lastItem < datoCuadricula.pagingInfo.itemCount && datoCuadricula.pagingInfo.lastItem + datoCuadricula.pagingInfo.batchSize > datoCuadricula.pagingInfo.itemCount}"/>
                <h:dataTable value="#{datoCuadricula.datoCuadriculaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdDatoCuadricula"/>
                        </f:facet>
                        <h:outputText value="#{item.idDatoCuadricula}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Dia"/>
                        </f:facet>
                        <h:outputText value="#{item.dia}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Horas"/>
                        </f:facet>
                        <h:outputText value="#{item.horas}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Dpto"/>
                        </f:facet>
                        <h:outputText value="#{item.dpto}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdCuadricula"/>
                        </f:facet>
                        <h:outputText value="#{item.idCuadricula}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{datoCuadricula.detailSetup}">
                            <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][datoCuadricula.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{datoCuadricula.editSetup}">
                            <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][datoCuadricula.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{datoCuadricula.destroy}">
                            <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][datoCuadricula.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{datoCuadricula.createSetup}" value="New DatoCuadricula"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
