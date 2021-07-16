package com.example.project_uts.model;

public class Obat {
    private String id;
    private String nama_obat;
    private String harga;
    private String stock;
    private String satuan;
    private String tgl_kadaluarsa;


    public Obat(){

    }
    public Obat(String id, String nama_obat, String harga, String stock, String satuan,String tgl_kadaluarsa){
        this.id=id;
        this.nama_obat=nama_obat;
        this.harga=harga;
        this.stock=stock;
        this.tgl_kadaluarsa=tgl_kadaluarsa;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama_obat() {
        return nama_obat;
    }
    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }
    public String getHarga() {
        return harga;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }
    public String getStock() {
        return stock;
    }
    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
    public String getSatuan() {
        return satuan;
    }
    public void setTgl_kadaluarsa(String tgl_kadaluarsa) {
        this.tgl_kadaluarsa = tgl_kadaluarsa;
    }
    public String getTgl_kadaluarsa() {
        return tgl_kadaluarsa;
    }
}
