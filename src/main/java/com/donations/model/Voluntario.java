package com.donations.model;

public class Voluntario {
    private int id;
    private String nome;
    private String telefone;
    private String especialidade;
    private int idEndereco;

    public Voluntario(int id, String nome, String telefone, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }
    // Getters e Setters...
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEspecialidade() { return especialidade; }
    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }
    @Override public String toString() { return nome + " (" + especialidade + ")"; }
}