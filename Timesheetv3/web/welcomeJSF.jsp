<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
<link rel="stylesheet" type="text/css" href="/Timesheetv3/faces/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="JavaServer Faces"/></h1>
                <h:form>
                    <h:commandLink action="#{usuario.listSetup}" value="Show All Usuario Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{notificaciones.listSetup}" value="Show All Notificaciones Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{notificacionPendiente.listSetup}" value="Show All NotificacionPendiente Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{notificacionDenegada.listSetup}" value="Show All NotificacionDenegada Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{datoCuadricula.listSetup}" value="Show All DatoCuadricula Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{cuadricula.listSetup}" value="Show All Cuadricula Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{autentificacion.listSetup}" value="Show All Autentificacion Items"/>
                </h:form>

        </body>
    </html>
</f:view>
