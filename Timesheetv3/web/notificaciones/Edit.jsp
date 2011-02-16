<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Notificaciones</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Notificaciones</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdNotificaciones:"/>
                <h:outputText value="#{notificaciones.notificaciones.idNotificaciones}" title="IdNotificaciones" />
                <h:outputText value="Tipo:"/>
                <h:inputText id="tipo" value="#{notificaciones.notificaciones.tipo}" title="Tipo" />
                <h:outputText value="Fecha (MM/dd/yyyy):"/>
                <h:inputText id="fecha" value="#{notificaciones.notificaciones.fecha}" title="Fecha" required="true" requiredMessage="The fecha field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="NotificacionDenegada:"/>
                <h:selectOneMenu id="notificacionDenegada" value="#{notificaciones.notificaciones.notificacionDenegada}" title="NotificacionDenegada" >
                    <f:selectItems value="#{notificacionDenegada.notificacionDenegadaItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="NotificacionPendiente:"/>
                <h:selectOneMenu id="notificacionPendiente" value="#{notificaciones.notificaciones.notificacionPendiente}" title="NotificacionPendiente" >
                    <f:selectItems value="#{notificacionPendiente.notificacionPendienteItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="IdCuadricula:"/>
                <h:selectOneMenu id="idCuadricula" value="#{notificaciones.notificaciones.idCuadricula}" title="IdCuadricula" required="true" requiredMessage="The idCuadricula field is required." >
                    <f:selectItems value="#{cuadricula.cuadriculaItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Remitente:"/>
                <h:selectOneMenu id="remitente" value="#{notificaciones.notificaciones.remitente}" title="Remitente" required="true" requiredMessage="The remitente field is required." >
                    <f:selectItems value="#{usuario.usuarioItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Destinatario:"/>
                <h:selectOneMenu id="destinatario" value="#{notificaciones.notificaciones.destinatario}" title="Destinatario" required="true" requiredMessage="The destinatario field is required." >
                    <f:selectItems value="#{usuario.usuarioItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{notificaciones.edit}" value="Save">
                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{notificaciones.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{notificaciones.listSetup}" value="Show All Notificaciones Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
