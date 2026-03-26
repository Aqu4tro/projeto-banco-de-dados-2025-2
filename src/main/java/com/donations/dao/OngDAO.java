package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.ONG;

public class OngDAO {
    
    public void salvar(ONG ong) {
        String sql = "INSERT INTO ONG (nome, cnpj, id_endereco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, ong.getNome());
            stmt.setString(2, ong.getCnpj());
            stmt.setInt(3, ong.getIdEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<ONG> listar() {
        List<ONG> lista = new ArrayList<>();
        String sql = "SELECT * FROM ONG ORDER BY id_ong DESC";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ONG o = new ONG(rs.getInt("id_ong"), rs.getString("nome"), rs.getString("cnpj"));
                o.setIdEndereco(rs.getInt("id_endereco"));
                lista.add(o);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}