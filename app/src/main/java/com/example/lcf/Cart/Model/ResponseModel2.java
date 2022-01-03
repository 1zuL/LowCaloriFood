package com.example.lcf.Cart.Model;

import java.util.List;

public class ResponseModel2 {
    private int kode;
    private String pesan;
    private List<DataModel2> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModel2> getData() {
        return data;
    }

    public void setData(List<DataModel2> data) {
        this.data = data;
    }
}
