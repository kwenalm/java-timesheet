<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Usuario Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Usuario Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{usuario.usuario.nif}" title="Nif" />
                <h:outputText value="Nombre:"/>
                <h:outputText value="#{usuario.usuario.nombre}" title="Nombre" />
                <h:outputText value="Apellido1:"/>
                <h:outputText value="#{usuario.usuario.apellido1}" title="Apellido1" />
                <h:outputText value="Apellido2:"/>
                <h:outputText value="#{usuario.usuario.apellido2}" title="Apellido2" />
                <h:outputText value="Tipo:"/>
                <h:outputText value="#{usuario.usuario.tipo}" title="Tipo" />
                <h:outputText value="Departamento:"/>
                <h:outputText value="#{usuario.usuario.departamento}" title="Departamento" />
                <h:outputText value="Autentificacion:"/>
                <h:panelGroup>
                    <h:outputText value="#{usuario.usuario.autentificacion}"/>
                    <h:panelGroup rendered="#{usuario.usuario.autentificacion != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{autentificacion.detailSetup}">
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="usuario"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{autentificacion.editSetup}">
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="usuario"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{autentificacion.destroy}">
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="usuario"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="NotificacionesCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty usuario.usuario.notificacionesCollection}" value="(No Items)"/>
                    <h:dataTable value="#{usuario.usuario.notificacionesCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty usuario.usuario.notificacionesCollection}">
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
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{notificaciones.editSetup}">
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{notificaciones.destroy}">
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="NotificacionesCollection1:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty usuario.usuario.notificacionesCollection1}" value="(No Items)"/>
                    <h:dataTable value="#{usuario.usuario.notificacionesCollection1}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty usuario.usuario.notificacionesCollection1}">
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
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{notificaciones.editSetup}">
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{notificaciones.destroy}">
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="CuadriculaCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty usuario.usuario.cuadriculaCollection}" value="(No Items)"/>
                    <h:dataTable value="#{usuario.usuario.cuadriculaCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty usuario.usuario.cuadriculaCollection}">
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
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cuadricula.editSetup}">
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cuadricula.destroy}">
                                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="usuario" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.UsuarioController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{usuario.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{usuario.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{usuario.createSetup}" value="New Usuario" />
            <br />
            <h:commandLink action="#{usuario.listSetup}" value="Show All Usuario Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
