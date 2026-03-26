package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.Campanha;

public class CampanhaDAO {
    
    // Agora o salvar exige os IDs da ONG e do Endereço!
    public void salvar(Campanha c, int idOng, int idEndereco) {
        String sql = "INSERT INTO CAMPANHA (nome, data_inicio, categoria, id_endereco, id_ong) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, c.getNome());
            stmt.setDate(2, c.getDataInicio());
            stmt.setString(3, c.getCategoria());
            stmt.setInt(4, idEndereco);
            stmt.setInt(5, idOng);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Campanha> listar() {
        List<Campanha> lista = new ArrayList<>();
        String sql = "SELECT id_campanha, nome, data_inicio, categoria FROM CAMPANHA ORDER BY id_campanha DESC";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Campanha(
                    rs.getInt("id_campanha"),
                    rs.getString("nome"),
                    rs.getDate("data_inicio"),
                    rs.getString("categoria")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}