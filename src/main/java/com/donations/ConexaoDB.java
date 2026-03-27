package com.donations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_doacoes";
    private static final String USUARIO = "usuario_ufca";
    private static final String SENHA = "enha_forte";
    private static Connection conn;

    public static Connection conectar() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
        return conn;
    }
}