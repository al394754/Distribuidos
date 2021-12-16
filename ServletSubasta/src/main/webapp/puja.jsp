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
		<h2>Puja no realizada. Compruebe que el art�culo exista, a�n no haya sido vendido y que su puja supera la m�xima puja. Adem�s, no puedes pujar por un producto que haya puesto en venta</h2>
	<% }
	else{
		String codart = (String) request.getAttribute("codart");
		String descr = (String) request.getAttribute("descr");
		String pujador = (String) request.getAttribute("pujador");
		String vendedor = (String) request.getAttribute("vendedor");
		Integer puja = (Integer) request.getAttribute("puja");
	%><h2>Enhorabuena. Tu puja es la m�xima actualmente.</h2>
	<h2>El art�culo <%=codart %> tiene el siguiente estado:</h2>
	<ul>
		<li>C�digo del art�culo: <%=codart %></li>
		<li>Descripci�n del art�culo: <%=descr %></li>
		<li>C�digo del vendedor: <%=vendedor %></li>
		<li>C�digo del cliente con la puja m�xima: <%=pujador %></li>
		<li>Valor de la puja m�xima: <%=puja %></li>
	</ul>
	<% }%>
	<br>
	<a class="menu" href="Menu.jsp">Men�</a>
</body>
</html>