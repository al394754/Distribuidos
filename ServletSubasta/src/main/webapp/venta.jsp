<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>venta</title>
</head>
<body>
<h2>Resultado de la venta</h2>
<% String resultado = (String) request.getAttribute("operacionRealizada");
	if(resultado == ""){%>
		<h2>Venta no realizada. Compruebe que el art�culo exista y que alguien haya pujado</h2>
	<% }
	else{
		String codart = (String) request.getAttribute("codart");
		String descr = (String) request.getAttribute("descr");
		String pujador = (String) request.getAttribute("pujador");
		Integer puja = (Integer) request.getAttribute("puja");
	%><h2>Enhorabuena. Has vendido el siguiente articulo.</h2>
	<br/>
	<h2>El art�culo <%=codart %> tiene el siguiente estado:</h2>
	<br/>
	<ul>
		<li>C�digo del art�culo: <%=codart %></li>
		<li>Descripci�n del art�culo: <%=descr %></li>
		<li>C�digo del cliente con la puja m�xima: <%=pujador %></li>
		<li>Valor de la puja m�xima: <%=puja %></li>
	</ul>
	<% }%>
	<a href="Menu.jsp">Men�</a>
</body>
</html>