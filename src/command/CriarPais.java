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

public class CriarPais implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		
		long populacao = Long.parseLong(pPopulacao);
		double area = Double.parseDouble(pArea);
		
		Pais pais = new Pais();
		PaisService ps = new PaisService();
		
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		
		ps.incluir(pais);
		ArrayList<Pais> lista = new ArrayList<>();
		lista.add(pais);
		session.setAttribute("lista", lista);
		RequestDispatcher view = request.getRequestDispatcher("ListarPaises.jsp");
		view.forward(request, response);
		
	}
}
