package com.donations.model;

public class TelefoneDoador {
    private int id; 
    private String numero; 
    private int ddd; 
    private String tipo; 
    private int idDoador;
    
    public TelefoneDoador(int id, String numero, int ddd, String tipo, int idDoador) {
         this.id = id; 
         this.numero = numero; 
         this.ddd = ddd; 
         this.tipo = tipo; 
         this.idDoador = idDoador; 
        }

    public int getId() { return id; }
    public String getNumero() { return numero; }
    public int getDdd() { return ddd; }
    public String getTipo() { return tipo; }
    public int getIdDoador() { return idDoador; }
}