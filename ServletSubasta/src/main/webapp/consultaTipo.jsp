<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>consultaTipo</title>
</head>
<body>
<p>Código	Descripcion	Vendedor Puja</p>
<br>
 <% Vector<String> articulosDeUnTipo = (Vector<String>) request.getAttribute("articulosDeUnTipo");
	if (articulosDeUnTipo == null){
		%><h1>No hay articulos de este tipo disponibles</h1>
	<%}
	else{
		for(String articulos: articulosDeUnTipo){		
			String[] campos = articulos.split("#");
	%>	
	
		<%= campos[0]	%><%=campos[2]	%><%=campos[3]	%><%=campos[5]%> <br>
	<% 	}
	}
%>
	<a href="Menu.jsp">Menú</a>
</body>
</html>