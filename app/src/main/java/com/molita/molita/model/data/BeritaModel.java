package com.molita.molita.model.data;

public class BeritaModel {
    private String id_edukasi;
    private String judul_edukasi;
    private String deskripsi_edukasi;
    private String image_url;
    private String created_at;
    private String like_user;
    private boolean isLoved;

    public BeritaModel() {
        this.isLoved = false;
    }

    public boolean isLoved() {
        return isLoved;
    }

    public void setLoved(boolean loved) {
        isLoved = loved;
    }

    public String getId_edukasi() {
        return id_edukasi;
    }

    public void setId_edukasi(String id_edukasi) {
        this.id_edukasi = id_edukasi;
    }

    public String getJudul_edukasi() {
        return judul_edukasi;
    }

    public void setJudul_edukasi(String judul_edukasi) {
        this.judul_edukasi = judul_edukasi;
    }

    public String getDeskripsi_edukasi() {
        return deskripsi_edukasi;
    }

    public void setDeskripsi_edukasi(String deskripsi_edukasi) {
        this.deskripsi_edukasi = deskripsi_edukasi;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLike_user() {
        return like_user;
    }

    public void setLike_user(String like_user) {
        this.like_user = like_user;
    }
}
