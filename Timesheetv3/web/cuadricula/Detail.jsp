<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Cuadricula Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Cuadricula Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdCuadricula:"/>
                <h:outputText value="#{cuadricula.cuadricula.idCuadricula}" title="IdCuadricula" />
                <h:outputText value="FechaInicio:"/>
                <h:outputText value="#{cuadricula.cuadricula.fechaInicio}" title="FechaInicio" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Estado:"/>
                <h:outputText value="#{cuadricula.cuadricula.estado}" title="Estado" />
                <h:outputText value="Nif:"/>
                <h:panelGroup>
                    <h:outputText value="#{cuadricula.cuadricula.nif}"/>
                    <h:panelGroup rendered="#{cuadricula.cuadricula.nif != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{usuario.detailSetup}">
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula.nif][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="cuadricula"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{usuario.editSetup}">
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula.nif][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="cuadricula"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{usuario.destroy}">
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula.nif][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="cuadricula"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="NotificacionesCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty cuadricula.cuadricula.notificacionesCollection}" value="(No Items)"/>
                    <h:dataTable value="#{cuadricula.cuadricula.notificacionesCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty cuadricula.cuadricula.notificacionesCollection}">
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
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cuadricula" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{notificaciones.editSetup}">
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cuadricula" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{notificaciones.destroy}">
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentNotificaciones" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notificaciones.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cuadricula" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="DatoCuadriculaCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty cuadricula.cuadricula.datoCuadriculaCollection}" value="(No Items)"/>
                    <h:dataTable value="#{cuadricula.cuadricula.datoCuadriculaCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty cuadricula.cuadricula.datoCuadriculaCollection}">
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
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][datoCuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cuadricula" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{datoCuadricula.editSetup}">
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][datoCuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cuadricula" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{datoCuadricula.destroy}">
                                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][datoCuadricula.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cuadricula" />
                                <f:param name="jsfcrud.relatedControllerType" value="vista.CuadriculaController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{cuadricula.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{cuadricula.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{cuadricula.createSetup}" value="New Cuadricula" />
            <br />
            <h:commandLink action="#{cuadricula.listSetup}" value="Show All Cuadricula Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
