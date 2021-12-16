<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>poneEnVenta</title>
<link rel="stylesheet" href="subasta.css">
</head>
<body>
<h2>Resultado de la puesta en venta</h2>
<% String respuesta = (String) request.getAttribute("venta");
	if(respuesta.length() == 0)
		%><h2>No se pudo realizar la puesta en venta</h2><%
	else{
		String[] puja = respuesta.split("#");%>
		<h2>Enhorabuena. Has podido poner en venta el siguiente artículo: </h2>
		<ul>
			<li>Código el artículo: <%=puja[0] %></li>
			<li>Descripción del artículo: <%=puja[2] %></li>
			<li>Precio de salida: <%=puja[5] %></li>
		</ul>
	<%}%>
	<br>
	<a class="menu" href="Menu.jsp">Menú</a>
</body>
</html>