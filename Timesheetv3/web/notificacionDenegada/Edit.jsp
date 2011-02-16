<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing NotificacionDenegada</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing NotificacionDenegada</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdNotificaciones:"/>
                <h:outputText value="#{notificacionDenegada.notificacionDenegada.idNotificaciones}" title="IdNotificaciones" />
                <h:outputText value="Motivo:"/>
                <h:inputTextarea rows="4" cols="30" id="motivo" value="#{notificacionDenegada.notificacionDenegada.motivo}" title="Motivo" />
                <h:outputText value="Notificaciones:"/>
                <h:selectOneMenu id="notificaciones" value="#{notificacionDenegada.notificacionDenegada.notificaciones}" title="Notificaciones" required="true" requiredMessage="The notificaciones field is required." >
                    <f:selectItems value="#{notificaciones.notificacionesItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{notificacionDenegada.edit}" value="Save">
                <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{notificacionDenegada.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{notificacionDenegada.listSetup}" value="Show All NotificacionDenegada Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
