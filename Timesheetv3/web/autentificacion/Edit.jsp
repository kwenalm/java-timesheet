<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Autentificacion</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Autentificacion</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{autentificacion.autentificacion.nif}" title="Nif" />
                <h:outputText value="Clave:"/>
                <h:inputText id="clave" value="#{autentificacion.autentificacion.clave}" title="Clave" required="true" requiredMessage="The clave field is required." />
                <h:outputText value="Usuario:"/>
                <h:selectOneMenu id="usuario" value="#{autentificacion.autentificacion.usuario}" title="Usuario" required="true" requiredMessage="The usuario field is required." >
                    <f:selectItems value="#{usuario.usuarioItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{autentificacion.edit}" value="Save">
                <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{autentificacion.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{autentificacion.listSetup}" value="Show All Autentificacion Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
