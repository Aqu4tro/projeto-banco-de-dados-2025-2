package com.donations.model;

public class Endereco {
    private int id;
    private String logradouro, numero, bairro, cidade;

    public Endereco() {}
    public Endereco(int id, String logradouro, String numero, String bairro, String cidade) {
        this.id = id; 
        this.logradouro = logradouro; 
        this.numero = numero; 
        this.bairro = bairro; 
        this.cidade = cidade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    @Override
    public String toString() { return logradouro + ", " + numero + " (" + bairro + ")"; }
}