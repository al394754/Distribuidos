<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="subasta.css">
<title>PantallaDeSalida</title>
</head>
<body>
<%	ServletContext contexto = getServletContext();
	String cliente = (String) contexto.getAttribute("codcli");%>
	<h1 class="sinColor">Hasta luego <%=cliente %></h1>
	<h1 class="sinColor">Vuelve pronto a tu casa de subastas favorita</h1>
	<%contexto.removeAttribute("codcli");%>
</body>
</html>