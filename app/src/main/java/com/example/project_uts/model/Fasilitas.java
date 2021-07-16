package com.example.project_uts.model;

public class Fasilitas {
    String id;
    String nama_kamar;
    String tarif;
    String fasilitas;
    String lantai;

    public Fasilitas(){

    }
    public Fasilitas(String id, String nama_kamar,String tarif,String fasilitas,String lantai){
      this.id=id;
      this.nama_kamar=nama_kamar;
      this.tarif=tarif;
      this.fasilitas=fasilitas;
      this.lantai=lantai;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama_kamar() {
        return nama_kamar;
    }
    public void setNama_kamar(String nama_kamar) {
        this.nama_kamar = nama_kamar;
    }
    public String getLantai() {
        return lantai;
    }
    public void setLantai(String lantai) {
        this.lantai = lantai;
    }
    public String getTarif() {
        return tarif;
    }
    public void setTarif(String tarif) {
        this.tarif = tarif;
    }
    public String getFasilitas() {
        return fasilitas;
    }
    public void setFasilitas(String fasiltas) {
        this.fasilitas = fasiltas;
    }
}
