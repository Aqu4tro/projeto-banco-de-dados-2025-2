package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.Endereco;

public class EnderecoDAO {
    public void salvar(Endereco e) {
        String sql = "INSERT INTO ENDERECO (logradouro, numero, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, e.getLogradouro());
            stmt.setString(2, e.getNumero());
            stmt.setString(3, e.getBairro());
            stmt.setString(4, e.getCidade());
            stmt.setString(5, "CE");
            stmt.setString(6, "63000-000");
            stmt.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<Endereco> listar() {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM ENDERECO ORDER BY id_endereco DESC";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Endereco(rs.getInt("id_endereco"), rs.getString("logradouro"), 
                        rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}