package com.molita.molita.model.data;

public class DataAnakModel {
    private String id_anak;
    private String nama_anak;
    private String tanggal_lahir;
    private String nama_imunisasi;
    private double berat_badan;
    private double tinggi_badan;
    private double lingkar_kepala;
    private String tanggal_pencatatan;

    // Getter and Setter methods
    public String getId_anak() {
        return id_anak;
    }

    public void setId_anak(String id_anak) {
        this.id_anak = id_anak;
    }

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getNama_imunisasi() {
        return nama_imunisasi;
    }

    public void setNama_imunisasi(String nama_imunisasi) {
        this.nama_imunisasi = nama_imunisasi;
    }

    public double getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(double berat_badan) {
        this.berat_badan = berat_badan;
    }

    public double getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(double tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public double getLingkar_kepala() {
        return lingkar_kepala;
    }

    public void setLingkar_kepala(double lingkar_kepala) {
        this.lingkar_kepala = lingkar_kepala;
    }

    public String getTanggal_pencatatan() {
        return tanggal_pencatatan;
    }

    public void setTanggal_pencatatan(String tanggal_pencatatan) {
        this.tanggal_pencatatan = tanggal_pencatatan;
    }
}
