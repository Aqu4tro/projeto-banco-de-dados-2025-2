package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.Doacao;

public class DoacaoDAO {
    
    public void salvar(Doacao d) throws SQLException {
        String sql = "INSERT INTO DOACAO (tipo, volume, data, descricao, id_doador, id_campanha) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, d.getTipo());
            stmt.setDouble(2, d.getVolume());
            stmt.setDate(3, d.getData());
            stmt.setString(4, d.getDescricao());
            stmt.setInt(5, d.getIdDoador());
            stmt.setInt(6, d.getIdCampanha());
            stmt.executeUpdate();
        }
    }

    public List<Doacao> listarDoacoes() {
        List<Doacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM DOACAO ORDER BY id_doacao DESC";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Doacao(rs.getInt("id_doacao"), rs.getString("tipo"), rs.getDouble("volume"),
                        rs.getDate("data"), rs.getString("descricao"), rs.getInt("id_doador"), rs.getInt("id_campanha")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM DOACAO WHERE id_doacao = ?";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public double getSomaTotal() {
        String sql = "SELECT SUM(volume) FROM DOACAO";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0.0;
    }
}