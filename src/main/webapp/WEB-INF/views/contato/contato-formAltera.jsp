<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%-- necessário para o jquery: datepicker --%>
		<link href="js/jquery-ui.css" rel="stylesheet">
		<script src="js/external/jquery/jquery.js"></script>
		<script src="js/jquery-ui.js"></script>

<script type="text/javascript">
	$(function(){
	$("#dataNascimento").datepicker({dateFormat: 'dd/mm/yy',
		showButtonPanel: true,
		showAnim: "slideDown",
		nextText: "Próximo",
		prevText: "Anterior",
		buttonText: "Escolha",
		closeText: "Feito", 
		currentText: "Hoje", 
		changeYear: true,
		changeMonth: true,
		yearRange: "-80:+1" ,
		minDate: new Date(1930, 1 - 1, 1),
		maxDate: "+2m +1w",
		monthNames: [ "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" ],
		monthNamesShort: [ "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez" ],
		dayNames: [ "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" ],
		dayNamesMin: [ "Do", "2a", "3a", "4a", "5a", "6a", "Sa" ]	
	});
});
	
	function chkemail(val) {
        if (val == "") 
        	{document.getElementById("tx_email").style.background = "yellow";
        	document.getElementById('mensform').innerHTML = "email: Não deixe em branco";
        	}
        else 
        	{document.getElementById("tx_email").style.background = "white";}
        
    	var filtro_mail = /^.+@.+\..{2,3}$/
       		if (!filtro_mail.test(val) || val=="") 
       		{document.getElementById("tx_email").style.background = "yellow";
       		 document.getElementById('mensform').innerHTML = "e-Mail: Inválido então eu preenchi para você"; 
       		 document.getElementById("tx_email").value="teste@teste.com";
       		}	
    }    
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Altera Contato</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
<%-- necessário para o displaytag --%>
</style> 
	<style type="text/css" media="all">
		@import url("css/maven-base.css");
		@import url("css/maven-theme.css");
		@import url("css/site.css");
		@import url("css/screen.css");
</style>

</head>
<body>
	<c:import url="/WEB-INF/views/cabecrodape/cabecalho.jsp" />
	<%-- <h3>Alterar Contato - ${contato.id}</h3>--%> 
	<h3>Alterar Contato</h3>
	<springForm:form method="POST" autocomplete="off" commandName="contato" action="ctl_contatoAltera">
		<table>
			<tr>
				<td>Id:</td>
				<td><springForm:input path="id" readonly="true"  style="border:none"/></td>
				<td><springForm:errors path="id" cssClass="error" /></td>
			</tr>				
			<tr>
				<td>Nome:</td>
				<td><springForm:input path="nome"  maxlength="50"/></td>
				<td><springForm:errors path="nome" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Endereço:</td>
				<td><springForm:input path="endereco"  maxlength="80"/></td>
				<td><springForm:errors path="endereco" cssClass="error" /></td>
			</tr>
			<tr>
				<td>eMail:</td>
				<td><springForm:input path="email" name="email" id="tx_email"  onchange="chkemail(this.value)" onblur="chkemail(this.value)" maxlength="50"/></td>
				<td><springForm:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Data Nascimento:</td>
				<td><springForm:input type="text" id="dataNascimento"
						path="dataNascimento" placeholder="dd/MM/yyyy" /></td>
				<td><springForm:errors path="dataNascimento" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Salvar"></td>
			</tr>
		</table>

	</springForm:form>	
	<p id="mensform"> Mensagem: Pressione Salvar para registrar </p>
	<c:import url="/WEB-INF/views/cabecrodape/rodape.jsp" />	
</body>
</html>