<%-- 
    Document   : buscar
    Created on : 11-feb-2011, 19:55:18
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
<link href="estilos/buscar.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="container">
  <div class="header"><a href="#"><img src="images/login.jpg" alt="Insert Logo Here" name="Insert_logo" width="800"  height="90" id="Insert_logo" style="background: #8090AB; display:block; margin-left:-10px" /></a>
    <!-- end .header --></div>
     <div id="identificacion">
                  <h:outputText value="identificado como:"/>
     </div>
  <div class="content"><div id="formulario">
          <h:form id="formularioBuscar" >
              
 <fieldset class="administracion">
     <legend>Criterios de busqueda de usuarios</legend>
     <div id="opcionesBusqueda"> 
         <h:selectOneRadio layout="pageDirection" id="radios" onchange="submit()" valueChangeListener="#{miBean.cambiarVisibilidad}">
         <f:selectItem id="nif" itemValue="nif" itemLabel="Buscar por nif"/>
         <f:selectItem id="nombre" itemValue="nombre" itemLabel="Buscar por nombre"/>
         <f:selectItem id="dpto" itemValue="dpto" itemLabel="Buscar por departamento"/>
         
        </h:selectOneRadio>
         <h:inputText id="inputNombreNif"  rendered="#{miBean.verNombreNif}" />
         
         <h:selectOneListbox rendered="#{miBean.verListaDptos}" id="listaDptos">
         <f:selectItem id="cuentas" itemValue="cuentas" itemLabel="Departamento de cuentas"/>
         <f:selectItem id="marketing" itemValue="marketing" itemLabel="Departamento de Marketing"/>
         <f:selectItem id="rrhh" itemValue="rrhh" itemLabel="Departamento de Recursos Humanos"/>

     </h:selectOneListbox>
     </div>
 </fieldset>
    
              
                            <div id="boton">
                                <h:commandButton type="button" id="proceder" value="Proceder"/>
                               
                                <h:commandButton type="reset" id="reset" value="Restaurar" style="margin-left:10px" />
                              
                                <h:commandButton type="button" id="volver" value="Volver" style="margin-left:10px" action="volver"/>
                            </div>
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

