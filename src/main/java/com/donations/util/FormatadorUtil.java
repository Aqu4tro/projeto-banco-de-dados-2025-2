package com.donations.util;

import javafx.scene.control.TextField;

public class FormatadorUtil {

    public static void aplicarMascaraCPF(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String clean = newValue.replaceAll("[^0-9]", "");
            if (clean.length() > 11) clean = clean.substring(0, 11);

            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < clean.length(); i++) {
                if (i == 3 || i == 6) formatted.append(".");
                if (i == 9) formatted.append("-");
                formatted.append(clean.charAt(i));
            }

            if (!newValue.equals(formatted.toString())) {
                textField.setText(formatted.toString());
            }
        });
    }

    public static void aplicarMascaraCNPJ(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String clean = newValue.replaceAll("[^0-9]", "");
            if (clean.length() > 14) clean = clean.substring(0, 14); // Limita a 14 dígitos

            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < clean.length(); i++) {
                if (i == 2 || i == 5) formatted.append(".");
                if (i == 8) formatted.append("/");
                if (i == 12) formatted.append("-");
                formatted.append(clean.charAt(i));
            }

            if (!newValue.equals(formatted.toString())) {
                textField.setText(formatted.toString());
            }
        });
    }

    public static void aplicarMascaraTelefone(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String clean = newValue.replaceAll("[^0-9]", "");
            if (clean.length() > 11) clean = clean.substring(0, 11); // Limita a 11 dígitos

            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < clean.length(); i++) {
                if (i == 0) formatted.append("(");
                if (i == 2) formatted.append(") ");
                if (i == 7) formatted.append("-");
                formatted.append(clean.charAt(i));
            }

            if (!newValue.equals(formatted.toString())) {
                textField.setText(formatted.toString());
            }
        });
    }
}