package com.donations.model;
import java.sql.Date;

public class Participacao {
    private int id; private Date dataFiliacao; 
    private Date dataEncerramento; 
    private String cargaHoraria; 
    private String cargo; 
    private int idCampanha; 
    private int idVoluntario;

    public Participacao(int id, Date dataFiliacao, Date dataEncerramento, String cargaHoraria, String cargo, int idCampanha, int idVoluntario) {
        this.id = id; this.dataFiliacao = dataFiliacao; 
        this.dataEncerramento = dataEncerramento; 
        this.cargaHoraria = cargaHoraria; 
        this.cargo = cargo; 
        this.idCampanha = idCampanha; 
        this.idVoluntario = idVoluntario;
    }
    public int getId() { return id; }
    public Date getDataFiliacao() { return dataFiliacao; }
    public Date getDataEncerramento() { return dataEncerramento; }
    public String getCargaHoraria() { return cargaHoraria; }
    public String getCargo() { return cargo; }
    public int getIdCampanha() { return idCampanha; }
    public int getIdVoluntario() { return idVoluntario; }
}