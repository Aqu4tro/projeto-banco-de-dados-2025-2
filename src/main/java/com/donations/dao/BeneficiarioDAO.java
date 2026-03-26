package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.Beneficiario;

public class BeneficiarioDAO {
    
    public void salvar(Beneficiario b) throws SQLException {
        String sql = "INSERT INTO BENEFICIARIO (nome, cpf, qtd_dependentes, id_endereco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, b.getNome());
            stmt.setString(2, b.getCpf());
            stmt.setInt(3, b.getQtdDependentes());
            stmt.setInt(4, b.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public List<Beneficiario> listar() throws SQLException {
        List<Beneficiario> lista = new ArrayList<>();
        String sql = "SELECT * FROM BENEFICIARIO";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Beneficiario b = new Beneficiario(
                    rs.getInt("id_beneficiario"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getInt("qtd_dependentes")
                );
                b.setIdEndereco(rs.getInt("id_endereco"));
                lista.add(b);
            }
        }
        return lista;
    }
}