<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Notificaciones Items</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Notificaciones Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Notificaciones Items Found)<br />" rendered="#{notificaciones.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{notificaciones.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{notificaciones.pagingInfo.firstItem + 1}..#{notificaciones.pagingInfo.lastItem} of #{notificaciones.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{notificaciones.prev}" value="Previous #{notificaciones.pagingInfo.batchSize}" rendered="#{notificaciones.pagingInfo.firstItem >= notificaciones.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{notificaciones.next}" value="Next #{notificaciones.pagingInfo.batchSize}" rendered="#{notificaciones.pagingInfo.lastItem + notificaciones.pagingInfo.batchSize <= notificaciones.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{notificaciones.next}" value="Remaining #{notificaciones.pagingInfo.itemCount - notificaciones.pagingInfo.lastItem}"
                               rendered="#{notificaciones.pagingInfo.lastItem < notificaciones.pagingInfo.itemCount && notificaciones.pagingInfo.lastItem + notificaciones.pagingInfo.batchSize > notificaciones.pagingInfo.itemCount}"/>
                <h:dataTable value="#{notificaciones.notificacionesItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdNotificaciones"/>
                        </f:facet>
                        <h:outputText value="#{item.idNotificaciones}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Tipo"/>
                        </f:facet>
                        <h:outputText value="#{item.tipo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Fecha"/>
                        </f:facet>
                        <h:outputText value="#{item.fecha}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="NotificacionDenegada"/>
                        </f:facet>
                        <h:outputText value="#{item.notificacionDenegada}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="NotificacionPendiente"/>
                        </f:facet>
                        <h:outputText value="#{item.notificacionPendiente}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdCuadricula"/>
                        </f:facet>
                        <h:outputText value="#{item.idCuadricula}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Remitente"/>
                        </f:facet>
                        <h:outputText value="#{item.remitente}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Destinatario"/>
                        </f:facet>
                        <h:outputText value="#{item.destinatario}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{notificaciones.detailSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificaciones.editSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificaciones.destroy}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{notificaciones.createSetup}" value="New Notificaciones"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
