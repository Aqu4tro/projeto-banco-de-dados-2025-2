package com.donations.model;

public class ItemEstoque {
    private int id;
    private String nomeItem;
    private double quantidade;
    private String unidadeMedida;
    private int idOng;

    public ItemEstoque(int id, String nomeItem, double quantidade, String unidadeMedida, int idOng) {
        this.id = id;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.idOng = idOng;
    }

    public int getId() { return id; }
    public String getNomeItem() { return nomeItem; }
    public double getQuantidade() { return quantidade; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public int getIdOng() { return idOng; }
}