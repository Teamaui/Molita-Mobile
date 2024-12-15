package com.molita.molita.model.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2/Molita/Public/index.php/"; // Ganti
    // dengan base URL API Anda
    private static Retrofit retrofit;

    // Singleton untuk Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // Konverter JSON ke objek Java
                    .build();
        }
        return retrofit;
    }

    // Mendapatkan instance ApiService
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}