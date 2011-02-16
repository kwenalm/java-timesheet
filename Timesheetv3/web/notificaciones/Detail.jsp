<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Notificaciones Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Notificaciones Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdNotificaciones:"/>
                <h:outputText value="#{notificaciones.notificaciones.idNotificaciones}" title="IdNotificaciones" />
                <h:outputText value="Tipo:"/>
                <h:outputText value="#{notificaciones.notificaciones.tipo}" title="Tipo" />
                <h:outputText value="Fecha:"/>
                <h:outputText value="#{notificaciones.notificaciones.fecha}" title="Fecha" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="NotificacionDenegada:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificaciones.notificaciones.notificacionDenegada}"/>
                    <h:panelGroup rendered="#{notificaciones.notificaciones.notificacionDenegada != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{notificacionDenegada.detailSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificacionDenegada.editSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificacionDenegada.destroy}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificacionDenegada" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.notificacionDenegada][notificacionDenegada.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="NotificacionPendiente:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificaciones.notificaciones.notificacionPendiente}"/>
                    <h:panelGroup rendered="#{notificaciones.notificaciones.notificacionPendiente != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{notificacionPendiente.detailSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{notificacionPendiente.editSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{notificacionPendiente.destroy}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentNotificacionPendiente" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.notificacionPendiente][notificacionPendiente.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="IdCuadricula:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificaciones.notificaciones.idCuadricula}"/>
                    <h:panelGroup rendered="#{notificaciones.notificaciones.idCuadricula != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{cuadricula.detailSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.idCuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{cuadricula.editSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.idCuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{cuadricula.destroy}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.idCuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Remitente:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificaciones.notificaciones.remitente}"/>
                    <h:panelGroup rendered="#{notificaciones.notificaciones.remitente != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{usuario.detailSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.remitente][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{usuario.editSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.remitente][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{usuario.destroy}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.remitente][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Destinatario:"/>
                <h:panelGroup>
                    <h:outputText value="#{notificaciones.notificaciones.destinatario}"/>
                    <h:panelGroup rendered="#{notificaciones.notificaciones.destinatario != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{usuario.detailSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.destinatario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{usuario.editSetup}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.destinatario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{usuario.destroy}">
                            <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones.destinatario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="notificaciones"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.NotificacionesController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{notificaciones.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{notificaciones.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notificaciones.notificaciones][notificaciones.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{notificaciones.createSetup}" value="New Notificaciones" />
            <br />
            <h:commandLink action="#{notificaciones.listSetup}" value="Show All Notificaciones Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
