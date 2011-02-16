<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New DatoCuadricula</title>
            <link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New DatoCuadricula</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{datoCuadricula.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="IdDatoCuadricula:"/>
                <h:inputText id="idDatoCuadricula" value="#{datoCuadricula.datoCuadricula.idDatoCuadricula}" title="IdDatoCuadricula" required="true" requiredMessage="The idDatoCuadricula field is required." />
                <h:outputText value="Dia (MM/dd/yyyy):"/>
                <h:inputText id="dia" value="#{datoCuadricula.datoCuadricula.dia}" title="Dia" required="true" requiredMessage="The dia field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Horas:"/>
                <h:inputText id="horas" value="#{datoCuadricula.datoCuadricula.horas}" title="Horas" required="true" requiredMessage="The horas field is required." />
                <h:outputText value="Dpto:"/>
                <h:inputText id="dpto" value="#{datoCuadricula.datoCuadricula.dpto}" title="Dpto" required="true" requiredMessage="The dpto field is required." />
                <h:outputText value="IdCuadricula:"/>
                <h:selectOneMenu id="idCuadricula" value="#{datoCuadricula.datoCuadricula.idCuadricula}" title="IdCuadricula" required="true" requiredMessage="The idCuadricula field is required." >
                    <f:selectItems value="#{cuadricula.cuadriculaItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{datoCuadricula.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{datoCuadricula.listSetup}" value="Show All DatoCuadricula Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
