<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>DatoCuadricula Detail</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>DatoCuadricula Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IdDatoCuadricula:"/>
                <h:outputText value="#{datoCuadricula.datoCuadricula.idDatoCuadricula}" title="IdDatoCuadricula" />
                <h:outputText value="Dia:"/>
                <h:outputText value="#{datoCuadricula.datoCuadricula.dia}" title="Dia" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Horas:"/>
                <h:outputText value="#{datoCuadricula.datoCuadricula.horas}" title="Horas" />
                <h:outputText value="Dpto:"/>
                <h:outputText value="#{datoCuadricula.datoCuadricula.dpto}" title="Dpto" />
                <h:outputText value="IdCuadricula:"/>
                <h:panelGroup>
                    <h:outputText value="#{datoCuadricula.datoCuadricula.idCuadricula}"/>
                    <h:panelGroup rendered="#{datoCuadricula.datoCuadricula.idCuadricula != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{cuadricula.detailSetup}">
                            <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula][datoCuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula.idCuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="datoCuadricula"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.DatoCuadriculaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{cuadricula.editSetup}">
                            <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula][datoCuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula.idCuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="datoCuadricula"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.DatoCuadriculaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{cuadricula.destroy}">
                            <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula][datoCuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula.idCuadricula][cuadricula.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="datoCuadricula"/>
                            <f:param name="jsfcrud.relatedControllerType" value="vista.DatoCuadriculaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{datoCuadricula.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula][datoCuadricula.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{datoCuadricula.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentDatoCuadricula" value="#{jsfcrud_class['vista.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][datoCuadricula.datoCuadricula][datoCuadricula.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{datoCuadricula.createSetup}" value="New DatoCuadricula" />
            <br />
            <h:commandLink action="#{datoCuadricula.listSetup}" value="Show All DatoCuadricula Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
