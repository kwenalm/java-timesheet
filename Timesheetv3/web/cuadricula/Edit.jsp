<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Cuadricula</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Cuadricula</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdCuadricula:"/>
                <h:outputText value="#{cuadricula.cuadricula.idCuadricula}" title="IdCuadricula" />
                <h:outputText value="FechaInicio (MM/dd/yyyy):"/>
                <h:inputText id="fechaInicio" value="#{cuadricula.cuadricula.fechaInicio}" title="FechaInicio" required="true" requiredMessage="The fechaInicio field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Estado:"/>
                <h:inputText id="estado" value="#{cuadricula.cuadricula.estado}" title="Estado" />
                <h:outputText value="NotificacionesCollection:"/>
                <h:selectManyListbox id="notificacionesCollection" value="#{cuadricula.cuadricula.jsfcrud_transform[jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.arrayToList].notificacionesCollection}" title="NotificacionesCollection" size="6" converter="#{notificaciones.converter}" >
                    <f:selectItems value="#{notificaciones.notificacionesItemsAvailableSelectMany}"/>
                </h:selectManyListbox>
                <h:outputText value="Nif:"/>
                <h:selectOneMenu id="nif" value="#{cuadricula.cuadricula.nif}" title="Nif" required="true" requiredMessage="The nif field is required." >
                    <f:selectItems value="#{usuario.usuarioItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="DatoCuadriculaCollection:"/>
                <h:selectManyListbox id="datoCuadriculaCollection" value="#{cuadricula.cuadricula.jsfcrud_transform[jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.arrayToList].datoCuadriculaCollection}" title="DatoCuadriculaCollection" size="6" converter="#{datoCuadricula.converter}" >
                    <f:selectItems value="#{datoCuadricula.datoCuadriculaItemsAvailableSelectMany}"/>
                </h:selectManyListbox>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{cuadricula.edit}" value="Save">
                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{cuadricula.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cuadricula.cuadricula][cuadricula.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{cuadricula.listSetup}" value="Show All Cuadricula Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
