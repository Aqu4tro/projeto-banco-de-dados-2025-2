package com.donations.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.donations.ConexaoDB;
import com.donations.model.TelefoneDoador;
import com.donations.model.TelefoneONG;

public class TelefoneDAO {
    public void salvarTelOng(TelefoneONG t) throws SQLException {
        String sql = "INSERT INTO TELEFONE_ONG (numero, ddd, tipo, id_ONG) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, t.getNumero()); 
            stmt.setInt(2, t.getDdd()); 
            stmt.setString(3, t.getTipo()); 
            stmt.setInt(4, t.getIdOng());
            stmt.executeUpdate();
        }
    }
    public void salvarTelDoador(TelefoneDoador t) throws SQLException {
        String sql = "INSERT INTO TELEFONE_DOADOR (numero, ddd, tipo, id_doador) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, t.getNumero()); 
            stmt.setInt(2, t.getDdd()); 
            stmt.setString(3, t.getTipo()); 
            stmt.setInt(4, t.getIdDoador());
            stmt.executeUpdate();
        }
    }
}