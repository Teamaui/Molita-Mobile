package com.molita.molita.model.data;

public class PosyanduModel {
    private String pos;
    private String id_jadwal_posyandu;
    private String id_posyandu;
    private String tanggal;
    private String jam_mulai;
    private String jam_selesai;

    // Constructor
//    public PosyanduModel(String pos, String id_jadwal_posyandu, String id_posyandu, String tanggal, String jam_mulai, String jam_selesai) {
//        this.pos = pos;
//        this.id_jadwal_posyandu = id_jadwal_posyandu;
//        this.id_posyandu = id_posyandu;
//        this.tanggal = tanggal;
//        this.jam_mulai = jam_mulai;
//        this.jam_selesai = jam_selesai;
//    }

    // Getters and Setters
    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getId_jadwal_posyandu() {
        return id_jadwal_posyandu;
    }

    public void setId_jadwal_posyandu(String id_jadwal_posyandu) {
        this.id_jadwal_posyandu = id_jadwal_posyandu;
    }

    public String getId_posyandu() {
        return id_posyandu;
    }

    public void setId_posyandu(String id_posyandu) {
        this.id_posyandu = id_posyandu;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }
}
