package com.donations.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.donations.ConexaoDB;
import com.donations.model.Entrega;

public class EntregaDAO {
    public void registrarEntrega(Entrega entrega) throws Exception {
        Connection conn = ConexaoDB.conectar();
        
        try {
            conn.setAutoCommit(false);

            String sqlCheck = "SELECT quantidade FROM ITEM_ESTOQUE WHERE id_item = ?";
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setInt(1, entrega.getIdItemEstoque());
                ResultSet rs = stmtCheck.executeQuery();
                if (rs.next()) {
                    double qtdAtual = rs.getDouble("quantidade");
                    if (qtdAtual < entrega.getQuantidade()) {
                        throw new Exception("Estoque insuficiente! Você tem apenas " + qtdAtual + " disponível.");
                    }
                } else {
                    throw new Exception("Item de estoque não encontrado.");
                }
            }

            String sqlInsert = "INSERT INTO ENTREGA (data_entrega, quantidade, id_beneficiario, id_item_estoque) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtIns = conn.prepareStatement(sqlInsert)) {
                stmtIns.setDate(1, entrega.getDataEntrega());
                stmtIns.setDouble(2, entrega.getQuantidade());
                stmtIns.setInt(3, entrega.getIdBeneficiario());
                stmtIns.setInt(4, entrega.getIdItemEstoque());
                stmtIns.executeUpdate();
            }

            // 3. Dá baixa na tabela de estoque (diminui a quantidade)
            String sqlUpdate = "UPDATE ITEM_ESTOQUE SET quantidade = quantidade - ? WHERE id_item = ?";
            try (PreparedStatement stmtUpd = conn.prepareStatement(sqlUpdate)) {
                stmtUpd.setDouble(1, entrega.getQuantidade());
                stmtUpd.setInt(2, entrega.getIdItemEstoque());
                stmtUpd.executeUpdate();
            }

            conn.commit();
            
        } catch(Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}