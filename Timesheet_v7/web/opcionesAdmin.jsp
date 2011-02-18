<%-- 
    Document   : opcionesAdmin
    Created on : 11-feb-2011, 18:46:23
    Author     : Borja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TimeSheet project</title>
<link href="estilos/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="container">
  <div class="header"><a href="#"><img src="images/login.jpg" alt="Insert Logo Here" name="Insert_logo" width="800"  height="90" id="Insert_logo" style="background: #8090AB; display:block; margin-left:-10px" /></a>
    <!-- end .header --></div>
     <div id="identificacion">
                  <h:outputText value="identificado como:"/>
     </div>
  <div class="content"><div id="formulario">
          <h:form id="formularioAdmin">


 <fieldset class="administracion">
     
     <legend>Opciones del administrador</legend>

                        <div id="alta">
                            <h:outputLink value="alta.jsp">
                                <h:graphicImage url="images/alta.gif"/>
                                <h:outputText value="Dar de Alta"/>
                            </h:outputLink>
                        </div>
                        <div id="baja">
                             <h:outputLink value="buscar.jsp">
                                 <h:graphicImage url="images/baja.png"/>
                                <h:outputText value="Dar de Baja"/>
                            </h:outputLink>
                        </div>
                        <div id="editar">
                             <h:outputLink value="buscar.jsp">
                                 <h:graphicImage url="images/editar.png" height="45" width="50"/>
                                <h:outputText value="Editar"/>
                            </h:outputLink>
                        </div>
 </fieldset>
                            
          </h:form>

 </div>


    <!-- end .content --></div>
  <div class="footer">
    Todos los derechos reservados - 2011
  </div>
</div>
</body>
</html>

</f:view>
