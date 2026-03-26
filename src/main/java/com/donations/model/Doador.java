package com.donations.model;

public class Doador {
    private int id;
    private String nome, cpf;
    private int idEndereco;

    public Doador() {}
    public Doador(int id, String nome, String cpf) {
        this.id = id; 
        this.nome = nome; 
        this.cpf = cpf;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }

    @Override
    public String toString() { return nome; }
}