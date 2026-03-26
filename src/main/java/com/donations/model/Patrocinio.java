package com.donations.model;
import java.sql.Date;

public class Patrocinio {
    private int id; private Date dataFiliacao; 
    private Date dataEncerramento; 
    private String tipo; 
    private double volume; 
    private int idCampanha; 
    private int idEmpresa;

    public Patrocinio(int id, Date dataFiliacao, Date dataEncerramento, String tipo, double volume, int idCampanha, int idEmpresa) {
        this.id = id; 
        this.dataFiliacao = dataFiliacao; 
        this.dataEncerramento = dataEncerramento; 
        this.tipo = tipo; 
        this.volume = volume; 
        this.idCampanha = idCampanha; 
        this.idEmpresa = idEmpresa;
    }
    
    public int getId() { return id; }
    public Date getDataFiliacao() { return dataFiliacao; }
    public Date getDataEncerramento() { return dataEncerramento; }
    public String getTipo() { return tipo; }
    public double getVolume() { return volume; }
    public int getIdCampanha() { return idCampanha; }
    public int getIdEmpresa() { return idEmpresa; }
}