package com.donations.model;

public class Empresa {
    private int id; 
    private String nome; 
    private String cnpj; 
    private String tipoEmpresa; 
    private String email; 
    private int idEndereco;

    public Empresa(int id, String nome, String cnpj, String tipoEmpresa, String email, int idEndereco) {
        this.id = id; 
        this.nome = nome; 
        this.cnpj = cnpj; 
        this.tipoEmpresa = tipoEmpresa; 
        this.email = email; 
        this.idEndereco = idEndereco;
    }

    public int getId() { return id; } public String getNome() { return nome; } public String getCnpj() { return cnpj; }
    public String getTipoEmpresa() { return tipoEmpresa; } public String getEmail() { return email; } public int getIdEndereco() { return idEndereco; }
    @Override public String toString() { return nome + " (" + cnpj + ")"; }
}