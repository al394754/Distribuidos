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
<%	HttpSession sesion = request.getSession();
	String cliente = (String) sesion.getAttribute("codcli");
	if(cliente == null) {
		RequestDispatcher vista = request.getRequestDispatcher("index.html");
		vista.forward(request, response);
	}else {%>
		<h1 class="sinColor">Hasta luego <%=cliente %></h1>
		<h1 class="sinColor">Vuelve pronto a tu casa de subastas favorita</h1>
		<%sesion.removeAttribute("codcli");
	}%>
</body>
</html>