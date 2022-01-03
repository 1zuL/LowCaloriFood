package com.example.lcf.Model;

public class DataModel {
    private int idproduk;
    private String namaproduk,deskripsi,hargaafter,gambar;

    public int getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(int idproduk) {
        this.idproduk = idproduk;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHargaafter() {
        return hargaafter;
    }

    public void setHargaafter(String hargaafter) {
        this.hargaafter = hargaafter;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
