package com.donations.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.donations.ConexaoDB;
import com.donations.model.Patrocinio;

public class PatrocinioDAO {
    public void salvar(Patrocinio p) throws SQLException {
        String sql = "INSERT INTO PATROCINIO (data_filiacao, data_encerramento, tipo, volume, id_campanha, id_empresa) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setDate(1, p.getDataFiliacao()); 
            stmt.setDate(2, p.getDataEncerramento()); 
            stmt.setString(3, p.getTipo());
            stmt.setDouble(4, p.getVolume()); 
            stmt.setInt(5, p.getIdCampanha()); 
            stmt.setInt(6, p.getIdEmpresa());
            stmt.executeUpdate();
        }
    }
}