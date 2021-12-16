<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>puja</title>
<link rel="stylesheet" href="subasta.css">
</head>
<body>
<h2>Resultado de la venta</h2>
<% String resultado = (String) request.getAttribute("operacionRealizada");
	if(resultado == ""){%>
		<h2>Puja no realizada. Compruebe que el artículo exista, aún no haya sido vendido y que su puja supera la máxima puja. Además, no puedes pujar por un producto que haya puesto en venta</h2>
	<% }
	else{
		String codart = (String) request.getAttribute("codart");
		String descr = (String) request.getAttribute("descr");
		String pujador = (String) request.getAttribute("pujador");
		String vendedor = (String) request.getAttribute("vendedor");
		Integer puja = (Integer) request.getAttribute("puja");
	%><h2>Enhorabuena. Tu puja es la máxima actualmente.</h2>
	<h2>El artículo <%=codart %> tiene el siguiente estado:</h2>
	<ul>
		<li>Código del artículo: <%=codart %></li>
		<li>Descripción del artículo: <%=descr %></li>
		<li>Código del vendedor: <%=vendedor %></li>
		<li>Código del cliente con la puja máxima: <%=pujador %></li>
		<li>Valor de la puja máxima: <%=puja %></li>
	</ul>
	<% }%>
	<br>
	<a class="menu" href="Menu.jsp">Menú</a>
</body>
</html>