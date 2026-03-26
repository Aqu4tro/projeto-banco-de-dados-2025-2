package com.donations.model;

public class TelefoneONG {
    private int id; 
    private String numero; 
    private int ddd; 
    private String tipo; 
    private int idOng;

    public TelefoneONG(int id, String numero, int ddd, String tipo, int idOng) {
         this.id = id; 
         this.numero = numero; 
         this.ddd = ddd; 
         this.tipo = tipo; 
         this.idOng = idOng; 
        }

    public int getId() { return id; }
    public String getNumero() { return numero; }
    public int getDdd() { return ddd; }
    public String getTipo() { return tipo; }
    public int getIdOng() { return idOng; }
}