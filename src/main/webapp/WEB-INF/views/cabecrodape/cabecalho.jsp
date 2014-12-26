<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<img src="<c:url value="/imagens/logo.png"/>" />

<%-- necessário para o displaytag --%>
    <style type="text/css" media="all">
      @import url("css/maven-base.css"); @import url("css/maven-theme.css"); @import url("css/site.css"); @import
      url("css/screen.css");
    </style>

<body>

	<h2>Agenda de Contatos do Fábio GM</h2>

	<springForm:form method="POST" action="ctl_contatoLista">
		<tr>
			<td colspan="1"><input type="submit" value="Listar Contatos"></td>
		</tr>
	</springForm:form>

	<springForm:form method="POST" action="ctl_contatoNovo">
		<tr>
			<td colspan="1"><input type="submit" value="Novo Contato"></td>
		</tr>
	</springForm:form>

</body>