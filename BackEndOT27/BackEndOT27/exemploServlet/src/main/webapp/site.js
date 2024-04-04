//OT8 inicio
//DESAFIO! Faça a validação dos demais 4 campos seguindo esse padrão (o campo select, apesar de diferente,
//tem validação igual devido a sua primeira option). Informação relevante: deve haver apenas UM return true,
//logo antes de fechar a função. Veja: 
//Para testar, sugerimos seguir os seguintes passos até que o formulário seja totalmente preenchido e
// enviado: 1. Deixe o formulário vazio; 2. Clique em Enviar; 3. Confira o alerta; 4. 
//Veja se o cursor foi para o campo indicado; a. Se sim, preencha-o e volte ao passo 2; b. Se não,
//veja o que pode estar errado, corrija e volte ao passo 1. 
function validaFaleConosco() {
	if (document.frmfaleconosco.txtname.value == "") {
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