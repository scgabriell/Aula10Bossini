package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

public class ListarPaises implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chave = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		
		PaisService ps = new PaisService();
		
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
		
		if(acao != null && acao.equals("buscar")) {
			if (chave != null && chave.length() > 0) {
				lista = ps.listarPaises(chave);
			} else {
				lista = ps.listar();
			}
			session.setAttribute("lista", lista);
		} else {
			session.setAttribute("lista", null);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarPaises.jsp");
		dispatcher.forward(request, response);
	}
}
