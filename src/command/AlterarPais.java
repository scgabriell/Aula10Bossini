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

public class AlterarPais implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		
		int id = Integer.parseInt(pId);
		long populacao = Long.parseLong(pPopulacao);
		double area = Double.parseDouble(pArea);
		
		Pais pais = new Pais();
		PaisService ps = new PaisService();
		
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		
		ps.atualizar(pais);
		ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
		int pos = Busca.busca(pais, lista);
		lista.remove(pos);
		lista.add(pos, pais);
		session.setAttribute("lista", lista);
		request.setAttribute("pais", pais);
		RequestDispatcher view = request.getRequestDispatcher("VisualizarPais.jsp");
		view.forward(request, response);
		
	}
}
