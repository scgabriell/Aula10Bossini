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

public class ExcluirPais implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Pais pais = new Pais();
		PaisService ps = new PaisService();
		
		String pId = request.getParameter("id");		
		int id = Integer.parseInt(pId);
		
		pais.setId(id);
		
		ps.excluir(pais.getId());
		ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
		lista.remove(Busca.busca(pais, lista));
		session.setAttribute("lista", lista);
		RequestDispatcher view = request.getRequestDispatcher("ListarPaises.jsp");
		view.forward(request, response);
	}
}
