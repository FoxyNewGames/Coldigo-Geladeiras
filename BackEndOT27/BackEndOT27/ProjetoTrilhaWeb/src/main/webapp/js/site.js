//OT8 inicio
//DESAFIO! Faça a validação dos demais 4 campos seguindo esse padrão (o campo select, apesar de diferente,
//tem validação igual devido a sua primeira option). Informação relevante: deve haver apenas UM return true,
//logo antes de fechar a função. Veja: 
//Para testar, sugerimos seguir os seguintes passos até que o formulário seja totalmente preenchido e
// enviado: 1. Deixe o formulário vazio; 2. Clique em Enviar; 3. Confira o alerta; 4. 
//Veja se o cursor foi para o campo indicado; a. Se sim, preencha-o e volte ao passo 2; b. Se não,
//veja o que pode estar errado, corrija e volte ao passo 1. 
//function validaFaleConosco() {

//var nome = document.frmfaleconosco.txtname.value;
//var expRegNome = new RegExp("^[A-zÀ-ü]{3,}([ ]{1}[A-zÀ-ü]{2,})+$");
//var expRegNome = new RegExp("^[A-zÀ-ü]+([ '-][A-zÀ-ü]+)*$");

//var fone = document.frmfaleconosco.txtfone.value;
//var expRegFone = new RegExp("^\(?[1-9]{2}\)??(?:[2-8]|9[1-9])[0-9]{3}\-?[0-9]{4}$");

//var expRegFone = new RegExp("^(?[1-9]{2})?[ .]?[2-9][0-9]{3,4}[ .-]?[0-9]{4}$");

//var expRegFone = new RegExp("^(?[1-9]{2})?[ .]?[2-9][0-9]{3,4}[ .-]?[0-9]{4}$");

//var email = document.frmfaleconosco.txtemail.value;
//var expRegEmail = new RegExp("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
/*
	if (!expRegNome.test(nome)) {
		alert("Preencha o campo Nome corretamente.");
		document.frmfaleconosco.txtname.focus();
		return false;
	}

	if (!expRegFone.test(fone)) {
		alert("Preencha o campo Telefone corretamente.");
		document.frmfaleconosco.txtfone.focus();
		return false;
	}

	if (!expRegEmail.test(email)) {
		alert("Preencha o campo Email corretamente.");
		document.frmfaleconosco.txtemail.focus();
		return false;
	}
	return true;
}

*/

function validaFaleConosco() {
	if (document.frmfaleconosco.txtnome.value == "") {
		alert("Preencha o campo nome");
		document.frmfaleconosco.txtnome.focus();
	} else {
		if (document.frmfaleconosco.txtfone.value == "") {
			alert("Preencha o campo telefone");
			document.frmfaleconosco.txtfone.focus();
		} else {
			if (document.frmfaleconosco.txtemail.value == "") {
				alert("Preencha o campo email");
				document.frmfaleconosco.txtemail.focus();
			}
			return true;
		}
	}
}


function validate() {

	var fone = document.getElementById("fone").value;
	var fone2 = document.getElementById("fone");
	var re = "/^[7-9][0-9]{9}$/";
	if (re.test(fone)) {
		alert("done");
		return true;
	}
	else {

		user2.style.border = "red solid 3px";
		return false;
	}
}
function verificaMotivo(motivo) {
	var elemento = document.getElementById("opcaoProduto");

	if (motivo == "PR") {

		var select = document.createElement("select");
		select.setAttribute("nome", "selproduto");

		var option = document.createElement("option");
		option.setAttribute("value", "");

		var texto = document.createTextNode("Escolha");
		option.appendChild(texto);

		select.appendChild(option);

		var option = document.createElement("option");
		option.setAttribute("value", "FR");

		var texto = document.createTextNode("Freezer");
		option.appendChild(texto);

		select.appendChild(option);


		var option = document.createElement("option");
		option.setAttribute("value", "GE");

		var texto = document.createTextNode("Geladeira");
		option.appendChild(texto);

		select.appendChild(option);

		elemento.appendChild(select);


	} else {
		if (elemento.firstChild)
			elemento.removeChild(elemento.firstChild);

	}
}

//DESAFIO! Na função de validação do formulário, entre as validações dos campos Motivo e Comentário, 
//crie uma validação para o campo Produto. Mas atenção: a lógica dessa validação deve envolver o campo Motivo,
// já que a própria existência do campo Produto depende dele... 

//OT8 FIm

$(document).ready(function() {
	$("header").load("/ProjetoTrilhaWeb/pages/site/cabecalho.html");
	$("nav").load("/ProjetoTrilhaWeb/pages/site/menu.html");
	$("footer").load("/ProjetoTrilhaWeb/pages/site/rodape.html");
});