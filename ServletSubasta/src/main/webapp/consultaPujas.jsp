<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>consultaPujas</title>
<link rel="stylesheet" href="subasta.css">
</head>
<body>
<% Vector<String> pujas = (Vector<String>) request.getAttribute("pujas");
	String cliente = (String) request.getAttribute("codcli");%>
	<h1 class="sinColor">Pujas de <%=cliente %></h1>
	<%if(pujas.size() == 0){%>
		<h1 class="sinColor">No hay articulos pujados por el cliente <%=cliente %></h1>
	<% }
	else{%>
		<table>
		<tr>
			<td><b>Código</b></td>
			<td><b>Descripción</b></td>
			<td><b>Vendedor</b></td>
			<td><b>Puja</b></td>
		</tr>
		<% for(String puja: pujas){		
			String[] campos = puja.split("#");%>	
		<tr>
			<td><%= campos[0]%></td>
			<td><%= campos[2]%></td>
			<td><%= campos[3]%></td>
			<td><%= campos[5]%></td>
		</tr>
		<%} %>
	</table>
	<%}%>
	<a class="menu" href="Menu.jsp">Menú</a>
</body>
</html>