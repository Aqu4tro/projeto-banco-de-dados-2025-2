package com.donations.model;

import java.sql.Date;

public class Doacao {
    private int id;
    private String tipo;
    private double volume;
    private Date data;
    private String descricao;
    private int idDoador;
    private int idCampanha;

    public Doacao() {}
    public Doacao(int id, String tipo, double volume, Date data, String descricao, int idDoador, int idCampanha) {
        this.id = id;
        this.tipo = tipo;
        this.volume = volume;
        this.data = data;
        this.descricao = descricao;
        this.idDoador = idDoador;
        this.idCampanha = idCampanha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getIdDoador() { return idDoador; }
    public void setIdDoador(int idDoador) { this.idDoador = idDoador; }
    public int getIdCampanha() { return idCampanha; }
    public void setIdCampanha(int idCampanha) { this.idCampanha = idCampanha; }
}