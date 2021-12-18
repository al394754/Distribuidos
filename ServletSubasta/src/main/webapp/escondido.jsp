<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servlet de Subastas</title>
<link rel="stylesheet" href="subasta.css"></head>
<body>
<%	ServletContext contexto = getServletContext(); 
	String cliente = (String) contexto.getAttribute("codcli");
	if(cliente != null){
		contexto.removeAttribute("codcli");
	}
%>
<img src="mazo.jpg">
<h1 class="conColor">Casa de subastas</h1>
<table>
	<tr>
		<td>
			<form action="ServletAccede" method="GET">
				Código de cliente: <input type="text" name="codcli" size="20" maxlength="8" required>
				<br><br>
				<input class="inputCentrado" type="submit" value="Acceder">
			</form>
		</td>
	</tr>
</table>
</body>
</html>