package com.donations.model;

import java.sql.Date;

public class Campanha {
    private int id;
    private String nome;
    private Date dataInicio;
    private String categoria;

    public Campanha() {}
    public Campanha(int id, String nome, Date dataInicio, String categoria) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return nome + " (" + categoria + ")";
    }
}