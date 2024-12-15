package com.molita.molita.model.repository;

import com.molita.molita.model.api.ApiService;
import com.molita.molita.model.api.RetrofitClient;
import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.model.data.ImunisasiModel;
import com.molita.molita.model.data.PosyanduModel;
import com.molita.molita.model.data.ResponseListData;

import retrofit2.Call;

public class PenjadwalanRepository {

    private ApiService apiService;

    public PenjadwalanRepository() {
        this.apiService = RetrofitClient.getApiService();
    }

    public Call<ResponseListData<AnakImunisasiModel>> getAnakImunisasi(String id) {
        return this.apiService.getAnakImunisasi(id);
    }

    public Call<ResponseListData<ImunisasiModel>> getAnakImunisasiById(String id) {
        return this.apiService.getAnakImunisasiById(id);
    }

    public Call<ResponseListData<PosyanduModel>> getAnakPosyandu(String id) {
        return this.apiService.getAnakPosyandu(id);
    }

}
