<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing NotificacionDenegada Items</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing NotificacionDenegada Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No NotificacionDenegada Items Found)<br />" rendered="#{notificacionDenegada.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{notificacionDenegada.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{notificacionDenegada.pagingInfo.firstItem + 1}..#{notificacionDenegada.pagingInfo.lastItem} of #{notificacionDenegada.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{notificacionDenegada.prev}" value="Previous #{notificacionDenegada.pagingInfo.batchSize}" rendered="#{notificacionDenegada.pagingInfo.firstItem >= notificacionDenegada.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{notificacionDenegada.next}" value="Next #{notificacionDenegada.pagingInfo.batchSize}" rendered="#{notificacionDenegada.pagingInfo.lastItem + notificacionDenegada.pagingInfo.batchSize <= notificacionDenegada.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{notificacionDenegada.next}" value="Remaining #{notificacionDenegada.pagingInfo.itemCount - notificacionDenegada.pagingInfo.lastItem}"
                               rendered="#{notificacionDenegada.pagingInfo.lastItem < notificacionDenegada.pagingInfo.itemCount && notificacionDenegada.pagingInfo.lastItem + notificacionDenegada.pagingInfo.batchSize > notificacionDenegada.pagingInfo.itemCount}"/>
                <h:dataTable value="#{notificacionDenegada.notificacionDenegadaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdNotificaciones"/>
                        </f:facet>
                        <h:outputText value="#{item.idNotificaciones}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Motivo"/>
                        </f:facet>
                        <h:outputText value="#{item.motivo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Notificaciones"/>
                        </f:facet>
                        <h:outputText value="#{item.notificaciones}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{notificacionDenegada.detailSetup}">
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificacionDenegada.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificacionDenegada.editSetup}">
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificacionDenegada.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificacionDenegada.destroy}">
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificacionDenegada.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{notificacionDenegada.createSetup}" value="New NotificacionDenegada"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
