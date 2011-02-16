<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>NotificacionDenegada Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>NotificacionDenegada Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdNotificaciones:"/>
                <h:outputText value="#{notificacionDenegada.notificacionDenegada.idNotificaciones}" title="IdNotificaciones" />
                <h:outputText value="Motivo:"/>
                <h:outputText value="#{notificacionDenegada.notificacionDenegada.motivo}" title="Motivo" />
                <h:outputText value="Notificaciones:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificacionDenegada.notificacionDenegada.notificaciones}"/>
                    <h:panelGroup rendered="#{notificacionDenegada.notificacionDenegada.notificaciones != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{notificaciones.detailSetup}">
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificacionDenegada"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionDenegadaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificaciones.editSetup}">
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificacionDenegada"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionDenegadaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificaciones.destroy}">
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificacionDenegada"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionDenegadaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{notificacionDenegada.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{notificacionDenegada.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificacionDenegada.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{notificacionDenegada.createSetup}" value="New NotificacionDenegada" />
            <br />
            <h:commandLink action="#{notificacionDenegada.listSetup}" value="Show All NotificacionDenegada Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
