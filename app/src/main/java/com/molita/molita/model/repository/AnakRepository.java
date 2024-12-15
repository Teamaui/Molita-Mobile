package com.molita.molita.model.repository;

import com.molita.molita.model.api.ApiService;
import com.molita.molita.model.api.RetrofitClient;
import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.model.data.AnakModel;
import com.molita.molita.model.data.DataAnakModel;
import com.molita.molita.model.data.ImunisasiModel;
import com.molita.molita.model.data.PosyanduModel;
import com.molita.molita.model.data.ResponseListData;

import retrofit2.Call;

public class AnakRepository {

    private ApiService apiService;

    public AnakRepository() {
        this.apiService = RetrofitClient.getApiService();
    }

    public Call<ResponseListData<AnakModel>> getAnakOrangTua(String id) {
        return this.apiService.getAnakById(id);
    }

    public Call<ResponseListData<DataAnakModel>> getAllDataAnakById(String id) {
        return this.apiService.getAllDataAnakById(id);
    }

}
