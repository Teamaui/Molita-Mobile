package com.molita.molita.viewmodel.Profile;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.model.data.OrangTuaModel;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.model.repository.AuthRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileVIewModel extends AndroidViewModel {

    private AuthRepository authRepository;
    private MutableLiveData<List<OrangTuaModel>> orangTuaModel = new MutableLiveData<>();

    public ProfileVIewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application.getApplicationContext());
    }

    public AuthRepository getAuthRepository() {
        return authRepository;
    }

    public void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public MutableLiveData<List<OrangTuaModel>> getOrangTuaModel() {
        return orangTuaModel;
    }

    public void setOrangTuaModel(MutableLiveData<List<OrangTuaModel>> orangTuaModel) {
        this.orangTuaModel = orangTuaModel;
    }

    public void getOrangTua(String id) {
        this.authRepository.getOrangTuaById(id).enqueue(new Callback<ResponseListData<OrangTuaModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<OrangTuaModel>> call, Response<ResponseListData<OrangTuaModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    orangTuaModel.setValue(responseListData.getData());
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<OrangTuaModel>> call, Throwable t) {

            }
        });
    }
}
