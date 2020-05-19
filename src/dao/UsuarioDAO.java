package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
    public boolean validar(Usuario usuario) {
        String sqlSelect = "SELECT username, password FROM usuario WHERE username = ? and password = ?";

        try (Connection conn = ConnectionFactory.obtemConexao(); 
                PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
                    stm.setString(1, usuario.getUsername());
                    stm.setString(2, usuario.getPassword());
                    try (ResultSet rs = stm.executeQuery();) {
                        return (rs.next()) ? true : false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
