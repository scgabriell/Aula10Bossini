package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;

public class PopulacaoDAO {
	
	public int incluir(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?,?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()) {
					pais.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Pais pais) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete)) {
			stm.setInt(1, pais.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Pais carregar(Pais pais) {
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if(rs.next()) {
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return pais;
	}
	
	public ArrayList <Pais> carregarTodosPaises() {
		ArrayList<Pais> paises = new ArrayList<>();
		String sqlSelectAll = "SELECT * FROM pais";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelectAll); 
				ResultSet rs = stm.executeQuery()) {
			while (rs.next()) {
				paises.add(new Pais(rs.getInt("id"), rs.getString("nome"), rs.getLong("populacao"), rs.getDouble("area")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises;
	}

	public ArrayList<Pais> listarPaises(String chave) {
		Pais pais;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM pais WHERE upper(nome) like ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();){
				while (rs.next()) {
					pais = new Pais();
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
					lista.add(pais);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return lista;
	}
}
