<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
		
</head>
<body>
	<c:import url="/WEB-INF/views/cabecrodape/cabecalho.jsp" />
	<br />
	<table border="1">
		<tr bgcolor="aaeeee">
			<th>id</th>
			<th>nome</th>
			<th>email</th>
			<th>endereco</th>
			<th>dataNascimento</th>
			<th>Alterar?</th>
			<th>Excluir?</th>
		</tr>
		
		<c:forEach items="${contato}" var="contato">
		<tr bgcolor="#${id.count % 2 == 0 ? 'D2DFF8' : 'DFF8D2' }">			
				<td>${contato.id}</td>
				<td>${contato.nome}</td>
				<td>${contato.email}</td>
				<td>${contato.endereco}</td>			
				<td>
					<fmt:formatDate 
						value="${contato.dataNascimento.time}"
						pattern="dd/MM/yyyy" />
				</td>

				<td><a href="ctl_contatoMostra?id=${contato.id}">Alterar</a></td>
				<td><a href="ctl_contatoExclui?id=${contato.id}">Excluir</a></td>
				
			</tr>
		</c:forEach>
	</table>
	<c:import url="/WEB-INF/views/cabecrodape/rodape.jsp" />
</body>
</html>