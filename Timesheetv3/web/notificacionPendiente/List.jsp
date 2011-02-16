<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing NotificacionPendiente Items</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing NotificacionPendiente Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No NotificacionPendiente Items Found)<br />" rendered="#{notificacionPendiente.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{notificacionPendiente.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{notificacionPendiente.pagingInfo.firstItem + 1}..#{notificacionPendiente.pagingInfo.lastItem} of #{notificacionPendiente.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{notificacionPendiente.prev}" value="Previous #{notificacionPendiente.pagingInfo.batchSize}" rendered="#{notificacionPendiente.pagingInfo.firstItem >= notificacionPendiente.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{notificacionPendiente.next}" value="Next #{notificacionPendiente.pagingInfo.batchSize}" rendered="#{notificacionPendiente.pagingInfo.lastItem + notificacionPendiente.pagingInfo.batchSize <= notificacionPendiente.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{notificacionPendiente.next}" value="Remaining #{notificacionPendiente.pagingInfo.itemCount - notificacionPendiente.pagingInfo.lastItem}"
                               rendered="#{notificacionPendiente.pagingInfo.lastItem < notificacionPendiente.pagingInfo.itemCount && notificacionPendiente.pagingInfo.lastItem + notificacionPendiente.pagingInfo.batchSize > notificacionPendiente.pagingInfo.itemCount}"/>
                <h:dataTable value="#{notificacionPendiente.notificacionPendienteItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IdNotificaciones"/>
                        </f:facet>
                        <h:outputText value="#{item.idNotificaciones}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Plazo"/>
                        </f:facet>
                        <h:outputText value="#{item.plazo}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
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
                        <h:commandLink value="Show" action="#{notificacionPendiente.detailSetup}">
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificacionPendiente.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificacionPendiente.editSetup}">
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificacionPendiente.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificacionPendiente.destroy}">
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificacionPendiente.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{notificacionPendiente.createSetup}" value="New NotificacionPendiente"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
