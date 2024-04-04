package br.com.gcontato.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdcionaContatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AdcionaContatoServlet() {
		super();
	}

//Basicamente ligar o server e ir direto para a pagina contato.html, mandar ela e ver se os nomes digitados foram lembrados apos o envio
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String nome = request.getParameter("nome");
		out.println(nome);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
