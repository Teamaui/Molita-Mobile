package com.molita.molita.model.data;

public class PertumbuhanModel {
    private String bulan_pencatatan;
    private float berat_badan;
    private float tinggi_badan;
    private float lingkar_kepala;

    public String getBulan_pencatatan() {
        return bulan_pencatatan;
    }

    public void setBulan_pencatatan(String bulan_pencatatan) {
        this.bulan_pencatatan = bulan_pencatatan;
    }

    public float getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(float berat_badan) {
        this.berat_badan = berat_badan;
    }

    public float getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(float tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public float getLingkar_kepala() {
        return lingkar_kepala;
    }

    public void setLingkar_kepala(float lingkar_kepala) {
        this.lingkar_kepala = lingkar_kepala;
    }
}