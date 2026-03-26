package com.donations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.donations.ConexaoDB;
import com.donations.model.ItemEstoque;

public class ItemEstoqueDAO {
    
    public void salvarOuSomar(ItemEstoque item) throws SQLException {
        String sql = "INSERT INTO ITEM_ESTOQUE (nome_item, quantidade, unidade_medida, id_ong) " +
                    "VALUES (?, ?, ?, ?) " +
                    "ON CONFLICT (nome_item, id_ong) " +
                    "DO UPDATE SET quantidade = ITEM_ESTOQUE.quantidade + EXCLUDED.quantidade";

        try (PreparedStatement stmt = ConexaoDB.conectar().prepareStatement(sql)) {
            stmt.setString(1, item.getNomeItem());
            stmt.setDouble(2, item.getQuantidade());
            stmt.setString(3, item.getUnidadeMedida());
            stmt.setInt(4, item.getIdOng());
            stmt.executeUpdate();
        }
    }

    public List<ItemEstoque> listar() {
        List<ItemEstoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM ITEM_ESTOQUE";
        try (Statement stmt = ConexaoDB.conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ItemEstoque(
                    rs.getInt("id_item"),
                    rs.getString("nome_item"),
                    rs.getDouble("quantidade"),
                    rs.getString("unidade_medida"),
                    rs.getInt("id_ong")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}