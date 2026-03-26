package com.donations.model;
import java.sql.Date;

public class Entrega {
    private int id;
    private Date dataEntrega;
    private double quantidade;
    private int idBeneficiario;
    private int idItemEstoque;

    public Entrega(int id, Date dataEntrega, double quantidade, int idBeneficiario, int idItemEstoque) {
        this.id = id;
        this.dataEntrega = dataEntrega;
        this.quantidade = quantidade;
        this.idBeneficiario = idBeneficiario;
        this.idItemEstoque = idItemEstoque;
    }

    public int getId() { return id; }
    public Date getDataEntrega() { return dataEntrega; }
    public double getQuantidade() { return quantidade; }
    public int getIdBeneficiario() { return idBeneficiario; }
    public int getIdItemEstoque() { return idItemEstoque; }
}