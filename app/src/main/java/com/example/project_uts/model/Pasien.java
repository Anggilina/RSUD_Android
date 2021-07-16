package com.example.project_uts.model;

import java.sql.Date;

public class Pasien {
    private String id;
    private String nama;
    private String tglLahir;
    private String gender;
    private String umur;
    private String alamat;
    private String nohp;

    public Pasien(){

    }
    public Pasien(String id, String nama, String tglLahir, String gender, String umur, String alamat, String nohp){
        this.id=id;
        this.nama=nama;
        this.tglLahir=tglLahir;
        this.gender=gender;
        this.umur=umur;
        this.alamat=alamat;
        this.nohp=nohp;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setNohp (String nohp) {
        this.nohp = nohp;
    }
    public String getNohp() {
        return nohp;
    }
    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }
    public String getTglLahir() {
        return tglLahir;
    }
    public void setUmur(String umur) {
        this.umur = umur;
    }
    public String getUmur() {
        return umur;
    }

}
