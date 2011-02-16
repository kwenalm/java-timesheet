<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Autentificacion Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Autentificacion Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{autentificacion.autentificacion.nif}" title="Nif" />
                <h:outputText value="Clave:"/>
                <h:outputText value="#{autentificacion.autentificacion.clave}" title="Clave" />
                <h:outputText value="Usuario:"/>
                <h:panelGroup>
                    <h:outputText value="#{autentificacion.autentificacion.usuario}"/>
                    <h:panelGroup rendered="#{autentificacion.autentificacion.usuario != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{usuario.detailSetup}">
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion.usuario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="autentificacion"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.AutentificacionController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{usuario.editSetup}">
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion.usuario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="autentificacion"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.AutentificacionController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{usuario.destroy}">
                            <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion.usuario][usuario.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="autentificacion"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.AutentificacionController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{autentificacion.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{autentificacion.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentAutentificacion" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][autentificacion.autentificacion][autentificacion.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{autentificacion.createSetup}" value="New Autentificacion" />
            <br />
            <h:commandLink action="#{autentificacion.listSetup}" value="Show All Autentificacion Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
