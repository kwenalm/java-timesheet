<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>NotificacionPendiente Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>NotificacionPendiente Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdNotificaciones:"/>
                <h:outputText value="#{notificacionPendiente.notificacionPendiente.idNotificaciones}" title="IdNotificaciones" />
                <h:outputText value="Plazo:"/>
                <h:outputText value="#{notificacionPendiente.notificacionPendiente.plazo}" title="Plazo" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Notificaciones:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificacionPendiente.notificacionPendiente.notificaciones}"/>
                    <h:panelGroup rendered="#{notificacionPendiente.notificacionPendiente.notificaciones != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{notificaciones.detailSetup}">
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificacionPendiente"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionPendienteController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificaciones.editSetup}">
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificacionPendiente"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionPendienteController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificaciones.destroy}">
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificacionPendiente"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionPendienteController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{notificacionPendiente.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{notificacionPendiente.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionPendiente.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{notificacionPendiente.createSetup}" value="New NotificacionPendiente" />
            <br />
            <h:commandLink action="#{notificacionPendiente.listSetup}" value="Show All NotificacionPendiente Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
