<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>consultaTipo</title>
<link rel="stylesheet" href="subasta.css">
</head>
<body>
<br>
 <% Vector<String> articulosDeUnTipo = (Vector<String>) request.getAttribute("articulosDeUnTipo");
	if (articulosDeUnTipo == null){
		%><h1 class="sinColor">No hay articulos de este tipo disponibles</h1>
	<%}
	else{%>
	<h1 class="sinColor">Artículos de tipo '<%=request.getAttribute("tipo") %>'</h1>
	<table>
		<tr>
			<td><b>Código</b></td>
			<td><b>Descripción</b></td>
			<td><b>Vendedor</b></td>
			<td><b>Puja</b></td>
		</tr>
		<% for(String articulos: articulosDeUnTipo){		
			String[] campos = articulos.split("#");%>	
		<tr>
			<td><%= campos[0]%></td>
			<td><%= campos[2]%></td>
			<td><%= campos[3]%></td>
			<td><%= campos[5]%></td>
		</tr>
		<%} %>
	</table>
	<%} %>
	<a class="menu" href="Menu.jsp">Menú</a>
</body>
</html>