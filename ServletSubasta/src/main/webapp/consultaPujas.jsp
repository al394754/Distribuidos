<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>consultaPujas</title>
</head>
<body>
<% Vector<String> pujas = (Vector<String>) request.getAttribute("pujas");
	String cliente = (String) request.getAttribute("codcli");
	if(pujas.size() == 0){%>
		<h1>No hay articulos pujados por el cliente <%=cliente %></h1>
	<% }
	else{
		for(String puja: pujas){%>
			<%=puja %>
		<% }
	}%>
	<a href="Menu.jsp">Menú</a>
</body>
</html>