<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Usuario</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Usuario</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{usuario.usuario.nif}" title="Nif" />
                <h:outputText value="Nombre:"/>
                <h:inputText id="nombre" value="#{usuario.usuario.nombre}" title="Nombre" required="true" requiredMessage="The nombre field is required." />
                <h:outputText value="Apellido1:"/>
                <h:inputText id="apellido1" value="#{usuario.usuario.apellido1}" title="Apellido1" required="true" requiredMessage="The apellido1 field is required." />
                <h:outputText value="Apellido2:"/>
                <h:inputText id="apellido2" value="#{usuario.usuario.apellido2}" title="Apellido2" />
                <h:outputText value="Tipo:"/>
                <h:inputText id="tipo" value="#{usuario.usuario.tipo}" title="Tipo" required="true" requiredMessage="The tipo field is required." />
                <h:outputText value="Departamento:"/>
                <h:inputText id="departamento" value="#{usuario.usuario.departamento}" title="Departamento" />
                <h:outputText value="Autentificacion:"/>
                <h:selectOneMenu id="autentificacion" value="#{usuario.usuario.autentificacion}" title="Autentificacion" >
                    <f:selectItems value="#{autentificacion.autentificacionItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="NotificacionesCollection:"/>
                <h:selectManyListbox id="notificacionesCollection" value="#{usuario.usuario.jsfcrud_transform[jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.arrayToList].notificacionesCollection}" title="NotificacionesCollection" size="6" converter="#{notificaciones.converter}" >
                    <f:selectItems value="#{notificaciones.notificacionesItemsAvailableSelectMany}"/>
                </h:selectManyListbox>
                <h:outputText value="NotificacionesCollection1:"/>
                <h:selectManyListbox id="notificacionesCollection1" value="#{usuario.usuario.jsfcrud_transform[jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.arrayToList].notificacionesCollection1}" title="NotificacionesCollection1" size="6" converter="#{notificaciones.converter}" >
                    <f:selectItems value="#{notificaciones.notificacionesItemsAvailableSelectMany}"/>
                </h:selectManyListbox>
                <h:outputText value="CuadriculaCollection:"/>
                <h:selectManyListbox id="cuadriculaCollection" value="#{usuario.usuario.jsfcrud_transform[jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method.arrayToList].cuadriculaCollection}" title="CuadriculaCollection" size="6" converter="#{cuadricula.converter}" >
                    <f:selectItems value="#{cuadricula.cuadriculaItemsAvailableSelectMany}"/>
                </h:selectManyListbox>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{usuario.edit}" value="Save">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{usuario.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{usuario.listSetup}" value="Show All Usuario Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
