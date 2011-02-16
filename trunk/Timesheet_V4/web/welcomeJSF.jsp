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
<link href="estilos/login.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="container">
  <div class="header"><a href="#"><img src="images/login.jpg" alt="Insert Logo Here" name="Insert_logo" width="800"  height="90" id="Insert_logo" style="background: #8090AB; display:block; margin-left:-10px" /></a>
    <!-- end .header --></div>
  <div class="content"><div id="formulario">
          <h:form  id="formularioLogeo" >


 <fieldset class="login">

			<legend>Identifíquese</legend>
			<div id="usuario">
                            Usuario:<h:inputText id="username" style="margin-left:37px" />
			</div>
			<div id="clave">
                            Contraseña: <h:inputSecret id="password" />
			</div>
            </fieldset>
                            <div id="boton">
                                <h:commandButton value="Enviar" action="opcionesAdmin" style="margin-left:-10px"/>
                                <h:commandButton value="Restaurar" type="reset" style="margin-left:10px"/>
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