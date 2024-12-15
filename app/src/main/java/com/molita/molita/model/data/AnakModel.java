package com.molita.molita.model.data;

import com.google.gson.annotations.SerializedName;

public class AnakModel {
    @SerializedName("id_anak")
    private String idAnak;

    @SerializedName("nama_anak")
    private String namaAnak;

    @SerializedName("tanggal_lahir")
    private String tanggalLahir;

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    // Tambahkan getter dan setter
    public String getIdAnak() {
        return idAnak;
    }

    public void setIdAnak(String idAnak) {
        this.idAnak = idAnak;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    @Override
    public String toString() {
        return namaAnak; // Spinner akan menampilkan nama anak
    }
}
