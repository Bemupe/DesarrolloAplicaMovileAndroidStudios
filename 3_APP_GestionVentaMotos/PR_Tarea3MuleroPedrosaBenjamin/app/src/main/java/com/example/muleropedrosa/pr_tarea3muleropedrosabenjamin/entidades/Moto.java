package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.entidades;

public class Moto {
    private String marca;
    private String modelo;
    private String  km;
    private String  anio;
    private String  cc;
    private String  cv;
    private String  precio;
    private Integer vendido;

    public Moto(Integer id, String marca, String modelo, String km, String anio, String cc, String cv, String precio, Integer vendido) {
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.anio = anio;
        this.cc = cc;
        this.cv = cv;
        this.precio = precio;
        this.vendido = vendido;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Integer getVendido() {
        return vendido;
    }

    public void setVendido(Integer vendido) {
        this.vendido = vendido;
    }
}
