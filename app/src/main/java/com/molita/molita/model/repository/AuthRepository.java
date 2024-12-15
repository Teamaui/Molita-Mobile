package com.molita.molita.model.repository;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.molita.molita.model.api.ApiService;
import com.molita.molita.model.api.RetrofitClient;
import com.molita.molita.model.data.OrangTuaModel;
import com.molita.molita.model.data.ResponseData;
import com.molita.molita.model.data.ResponseListData;

import retrofit2.Call;

public class AuthRepository {
    private ApiService apiService;
    private SharedPreferences sharedPreferences;
    public AuthRepository(Context context) {
        apiService = RetrofitClient.getApiService();
         sharedPreferences = context.getSharedPreferences("UserPrefs",
                MODE_PRIVATE);
    }

    public void saveUserId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Simpan ID atau username
        editor.putString("userId", id); // Ganti dengan ID yang sesuai
        editor.apply(); // Atau gunakan editor.commit() untuk sinkronisasi langsung
    }

    public void saveNameIbu(String ibu) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Simpan ID atau username
        editor.putString("userIbu", ibu); // Ganti dengan ID yang sesuai
        editor.apply(); // Atau gunakan editor.commit() untuk sinkronisasi langsung
    }

    public String getUserId() {
        return sharedPreferences.getString("userId", null);
    }

    public String getUserNameIbu() {
        return sharedPreferences.getString("userIbu", null);
    }

    public void clearAllData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Menghapus semua data
        editor.clear(); // Menghapus semua data dalam SharedPreferences
        editor.apply(); // Atau gunakan editor.commit() untuk sinkronisasi langsung
    }


    // Register
    public Call<ResponseData> register(String email, String username,
                                       String password) {
        return apiService.register(email, username, password);
    }

    // Login
    public Call<ResponseListData<OrangTuaModel>> login(String email, String password) {
        return apiService.login(email, password);
    }

    // Profile
    public Call<ResponseListData<OrangTuaModel>> getOrangTuaById(String id) {
        return apiService.getOrangTuaById(id);
    }
}
