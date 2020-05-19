package service;

import java.util.ArrayList;
import java.util.List;

import dao.PopulacaoDAO;
import model.Pais;

public class PaisService {

	private PopulacaoDAO dao;
	
	public PaisService() {
		dao = new PopulacaoDAO();
	}
	
	public int incluir(Pais pais) {
		return dao.incluir(pais);
	}
	
	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}
	
	public void excluir(Pais pais) {
		dao.excluir(pais);
	}
	
	public void excluir(int id) {
		dao.excluir(new Pais(id));
	}
	
	public Pais carregar(Pais pais) {
		return dao.carregar(pais);
	}
	
	public Pais carregar(int id) {
		return dao.carregar(new Pais(id));
	}
	
	public ArrayList <Pais> listar() {
		return dao.carregarTodosPaises();
	}
	
	public Pais carregarMaiorPopulacao() {
		Pais maior = carregar(1);
		List<Pais> paises = dao.carregarTodosPaises();
		for (Pais pais: paises) {
			if (pais.getPopulacao() > maior.getPopulacao()) maior = pais;
		}
		return maior;
	}
	
	public Pais carregarMenorArea() {
		Pais menor = carregar(1);
		List<Pais> paises = dao.carregarTodosPaises();
		for (Pais pais: paises) {
			if (pais.getArea() < menor.getArea()) menor = pais;
		}
		return menor;
	}
	
	public Pais[] carrega3paises() {
		Pais[] paises = new Pais[3];
		
		for (int id = 0; id < 3; id++) {
			paises[id] = dao.carregar(new Pais(id+1));
		}
		
		return paises;
	}

	public ArrayList<Pais> listarPaises(String chave) {
		return dao.listarPaises(chave);
	}
	
	
}
