package com.molita.molita.model.repository;

import com.molita.molita.model.api.ApiService;
import com.molita.molita.model.api.RetrofitClient;
import com.molita.molita.model.data.EdukasiModel;
import com.molita.molita.model.data.JenisEdukasiModel;
import com.molita.molita.model.data.ResponseData;
import com.molita.molita.model.data.ResponseListData;

import retrofit2.Call;

public class EdukasiRepository {

    private ApiService apiService;

    public EdukasiRepository() {
        this.apiService = RetrofitClient.getApiService();
    }

    public Call<ResponseListData<EdukasiModel>> edukasi() {
        return this.apiService.getEdukasi();
    }

    public Call<ResponseListData> showEdukasi() {
        return this.showEdukasi();
    }

    public Call<ResponseListData<EdukasiModel>> getEdukasiBySlug(String slug) {
        return this.apiService.getEdukasiBySlug(slug);
    }

    public Call<ResponseListData<JenisEdukasiModel>> jenisEdukasi() {
        return this.apiService.getJenisEdukasi();
    }
}
