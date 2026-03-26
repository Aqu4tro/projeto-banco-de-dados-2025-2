package com.donations.dao;

import com.donations.ConexaoDB;
import com.donations.model.Voluntario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO {
    public void salvar(Voluntario v) {
        String sql = "INSERT INTO VOLUNTARIO (nome, telefone, especialidade, id_endereco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, v.getNome());
            stmt.setString(2, v.getTelefone());
            stmt.setString(3, v.getEspecialidade());
            stmt.setInt(4, v.getIdEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Voluntario> listar() {
        List<Voluntario> lista = new ArrayList<>();
        String sql = "SELECT * FROM VOLUNTARIO";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Voluntario v = new Voluntario(rs.getInt("id_voluntario"), rs.getString("nome"), 
                                              rs.getString("telefone"), rs.getString("especialidade"));
                v.setIdEndereco(rs.getInt("id_endereco"));
                lista.add(v);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}