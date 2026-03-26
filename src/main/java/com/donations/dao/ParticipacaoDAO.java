package com.donations.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.donations.ConexaoDB;
import com.donations.model.Participacao;

public class ParticipacaoDAO {
    public void salvar(Participacao p) throws SQLException {
        String sql = "INSERT INTO PARTICIPACAO (data_filiacao, data_encerramento, carga_horaria, cargo, id_campanha, id_voluntario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setDate(1, p.getDataFiliacao()); stmt.setDate(2, p.getDataEncerramento()); stmt.setString(3, p.getCargaHoraria());
            stmt.setString(4, p.getCargo()); stmt.setInt(5, p.getIdCampanha()); stmt.setInt(6, p.getIdVoluntario());
            stmt.executeUpdate();
        }
    }
}