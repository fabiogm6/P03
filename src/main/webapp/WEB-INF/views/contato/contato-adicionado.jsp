<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulário - Sucesso</title>
</head>
<body>
	<c:import url="/WEB-INF/views/cabecrodape/cabecalho.jsp" />
	<h1>Contato Adicionado com Sucesso !</h1>
	<hr />
	<p>Nome: ${param.nome}</p>
	<p>Endereço: ${param.endereco}</p>
	<p>eMail: ${param.email}</p>
	<p>Data Nascimento: ${param.dataNascimento}</p>
	<c:import url="/WEB-INF/views/cabecrodape/rodape.jsp" />
</body>
</html>