<%-- 
    Document   : alta
    Created on : 14-feb-2011, 12:41:49
    Author     : Java I
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
<link href="estilos/alta.css" rel="stylesheet" type="text/css" />
</head>

<body>
   
    <script validaciones>
      
        function averigua_letra_dni(numeros_dni){
		lista_letras="TRWAGMYFPDXBNJZSQVHLCKET"; // Lista codificada de letras mayúsculas.
		posicion=numeros_dni%23; // Resto de la división entera del DNI numérico entre 23.
		letra_dni=lista_letras.substring(posicion,posicion+1); // Obtenemos la letra.
		return letra_dni; // Devuelve la letra mayúscula para a las 8 cifras del D.N.I.
	}
        function esDNI(){

	nombre_campo="D.N.I.";
	valor = document.getElementById('formularioAlta:nif').value;
	if (valor.length!=9){
		alert("El campo: "+nombre_campo+" debe contener 9 caracteres.");
		return "";
	}
	else{
		numeros = valor.substring(0,8); // Tomamos los 8 primeros caracteres.
		letra = valor.substring(8); // Tomamos el carácter en el noveno lugar.
		if(isNaN(numeros)){
			alert("El campo: "+nombre_campo+" no empieza por 9 cifras numéricas.");
			return "";
		}
		else if (! isNaN(letra)){
			alert("El campo: "+nombre_campo+" no debe terminar en una cifra numérica.");
			return "";
		}
		else{
			letra=letra.toUpperCase(); ; // Ponemos la letra en mayúsculas.
			letra_correcta = averigua_letra_dni(numeros);
			if(letra != letra_correcta){
				alert("El campo: "+nombre_campo+" contiene una letra incorrecta.");
				return "";
			}
		}
	}
	dni_correcto=numeros+letra;
  	document.getElementById('formularioAlta:nif').value=dni_correcto;
	return dni_correcto;
    }
      function validaEnviar(){
            nif=document.getElementById('formularioAlta:nif').value;
            nombre=document.getElementById('formularioAlta:nombre').value;
            apellido1=document.getElementById('formularioAlta:apellido1').value;
            apellido2=document.getElementById('formularioAlta:apellido2').value;
            password=document.getElementById('formularioAlta:password').value;
            password2=document.getElementById('formularioAlta:password2').value;
            dpto=document.getElementById('formularioAlta:listaDptos').value;

        if(nombre.length==0||apellido1.length==0||password.length==0||
            password2.length==0||dpto.length==0)
        {
                alert("Alguno de los campos requeridos no ha sido introducido");
           }
          else{
              if(password!=password2)
                  alert("Las contraseñas deben ser iguales");
              else
                 esDNI();
          }
        

        }

       
    </script>
<div class="container">
  <div class="header"><a href="#"><img src="images/login.jpg" alt="Insert Logo Here" name="Insert_logo" width="800"  height="90" id="Insert_logo" style="background: #8090AB; display:block; margin-left:-10px" /></a>
    <!-- end .header --></div>
     <div id="identificacion">
                  <h:outputText value="identificado como:"/>
     </div>
  <div class="content"><div id="formulario">
          <h:form id="formularioAlta" prependId="true" >

 <fieldset class="administracion">
     <legend>Datos del usuario</legend>
     <div id="datosAlta">
         <table id="tablaDatosAlta">
             <tr>
                 <td><h:outputText value="Nombre:"/></td>
                 <td> <h:inputText id="nombre" /></td>
             </tr>
             <tr>
                 <td><h:outputText value="Primer apellido:"/></td>
                 <td><h:inputText id="apellido1" /></td>
             </tr>
             <tr>
                 <td> <h:outputText value="Segundo apellido:"/></td>
                 <td> <h:inputText id="apellido2" /></td>
             </tr>
             <tr>
                 <td><h:outputText value="Nif:"/></td>
                 <td><h:inputText id="nif" /></td>
             </tr>
             <tr>
                 <td><h:outputText value="Password:" /></td>
                 <td><h:inputSecret id="password"/></td>
             </tr>
             <tr>
                 <td><h:outputText value="Repetición de password:" /></td>
                 <td><h:inputSecret id="password2"/></td>
             </tr>
              <tr>
                  <td><h:outputText value="Departamento:"/></td>
                  <td>
                      <h:selectOneListbox id="listaDptos" style="margin-left:10px">
                          <f:selectItem id="cuentas" itemValue="Departamento de Cuentas"/>
                          <f:selectItem id="marketing" itemValue="Departamento de Marketing"/>
                          <f:selectItem id="rrhh" itemValue="Departamento de Recursos Humanos"/>
                      </h:selectOneListbox>
                  </td>
             </tr>
         </table>
         
        

         

        

     </div>
 </fieldset>


                            <div id="boton">
                                <h:commandButton  id="proceder" value="Proceder" onclick="validaEnviar()" />

                                <h:commandButton type="reset" id="reset" value="Restaurar" style="margin-left:10px" />

                                <h:commandButton  id="volver" value="Volver" style="margin-left:10px" action="volver"/>
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