package com.donations.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.Empresa;

public class EmpresaDAO {
    public void salvar(Empresa e) throws SQLException {
        String sql = "INSERT INTO EMPRESA (nome, cnpj, tipo_empresa, email, id_endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, e.getNome()); 
            stmt.setString(2, e.getCnpj()); 
            stmt.setString(3, e.getTipoEmpresa()); 
            stmt.setString(4, e.getEmail()); 
            stmt.setInt(5, e.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public List<Empresa> listar() throws SQLException {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPRESA";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Empresa(
                    rs.getInt("id_empresa"), rs.getString("nome"), rs.getString("cnpj"),
                    rs.getString("tipo_empresa"), rs.getString("email"), rs.getInt("id_endereco")
                ));
            }
        }
        return lista;
    }
}