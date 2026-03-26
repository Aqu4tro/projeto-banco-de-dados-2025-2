package com.donations.model;

public class Beneficiario {
    private int id;
    private String nome;
    private String cpf;
    private int qtdDependentes;
    private int idEndereco;

    public Beneficiario(int id, String nome, String cpf, int qtdDependentes) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.qtdDependentes = qtdDependentes;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public int getQtdDependentes() { return qtdDependentes; }
    
    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}