package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.Doador;

public class DoadorDAO {
    public void salvar(Doador d) {
        String sql = "INSERT INTO DOADOR (nome, cpf, id_endereco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getCpf());
            stmt.setInt(3, d.getIdEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Doador> listar() {
        List<Doador> lista = new ArrayList<>();
        String sql = "SELECT * FROM DOADOR ORDER BY nome ASC";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Doador(rs.getInt("id_doador"), rs.getString("nome"), rs.getString("cpf")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}