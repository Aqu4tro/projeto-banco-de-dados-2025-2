package com.donations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/donations";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";
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