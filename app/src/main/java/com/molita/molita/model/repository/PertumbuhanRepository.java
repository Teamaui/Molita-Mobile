package com.molita.molita.model.repository;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.molita.molita.model.api.ApiService;
import com.molita.molita.model.api.RetrofitClient;
import com.molita.molita.model.data.PertumbuhanModel;
import com.molita.molita.model.data.ResponseListData;

import retrofit2.Call;

public class PertumbuhanRepository {

    private ApiService apiService;
    private SharedPreferences sharedPreferences;

    public PertumbuhanRepository(Context context) {
        this.apiService = RetrofitClient.getApiService();
        sharedPreferences = context.getSharedPreferences("UserPrefs",
                MODE_PRIVATE);
    }

    public void saveIdAnak(String idAnak) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Simpan ID atau username
        editor.putString("idAnak", idAnak); // Ganti dengan ID yang sesuai
        editor.apply(); // Atau gunakan editor.commit() untuk sinkronisasi langsung
    }

    public String getIdAnak() {
        // Ambil ID yang disimpan sebelumnya, atau null jika tidak ada
        return sharedPreferences.getString("idAnak", null);
    }

    public void updateIdAnak(String newIdAnak) {
        // Update ID dengan yang baru
        saveIdAnak(newIdAnak);
    }


    public Call<ResponseListData<PertumbuhanModel>> getPertumbuhan() {
        return this.apiService.getPertumbuhan();
    }

    public Call<ResponseListData<PertumbuhanModel>> getPertumbuhanById(String id) {
        return this.apiService.getPertumbuhanById(id);
    }


}
