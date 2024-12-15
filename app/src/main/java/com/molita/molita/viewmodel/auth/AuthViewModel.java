package com.molita.molita.viewmodel.auth;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.molita.molita.model.data.OrangTuaModel;
import com.molita.molita.model.data.ResponseData;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.model.repository.AuthRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository userRepository;
    private MutableLiveData<String> statusMessage = new MutableLiveData<>();
    private MutableLiveData<String> token = new MutableLiveData<>();

    public MutableLiveData<List<OrangTuaModel>> getOrangTuaModel() {
        return orangTuaModel;
    }

    public void setOrangTuaModel(MutableLiveData<List<OrangTuaModel>> orangTuaModel) {
        this.orangTuaModel = orangTuaModel;
    }

    private MutableLiveData<List<OrangTuaModel>> orangTuaModel = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRegistrationSuccessful = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        userRepository = new AuthRepository(application.getApplicationContext());
    }

    public MutableLiveData<String> getStatusMessage() {
        return statusMessage;
    }

    public MutableLiveData<String> getToken() {
        return token;
    }

    public MutableLiveData<Boolean> getIsRegistrationSuccessful() {
        return isRegistrationSuccessful;
    }


    // Register
    public void register(String email, String username, String password) {
        userRepository.register(email, username, password).enqueue(new Callback<ResponseData>() {
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        // Registrasi berhasil, pindah halaman
                        isRegistrationSuccessful.setValue(true);
                    } else {
                        // Gagal, tampilkan pesan error
                        statusMessage.setValue(response.body().getMessage());
                        isRegistrationSuccessful.setValue(false);
                    }
                } else {
                    // Respons tidak sukses
                    statusMessage.setValue("Registration failed!");
                    isRegistrationSuccessful.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                // Gagal karena jaringan atau error lainnya
                statusMessage.setValue("Error: " + t.getMessage());
                isRegistrationSuccessful.setValue(false);
            }
        });
    }


    public void login(String email, String password) {
        userRepository.login(email, password).enqueue(new Callback<ResponseListData<OrangTuaModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<OrangTuaModel>> call, Response<ResponseListData<OrangTuaModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        isRegistrationSuccessful.setValue(true);
                        ResponseListData responseListData = response.body();
                        orangTuaModel.setValue(responseListData.getData());
                    } else {
                        statusMessage.setValue(response.body().getMessage());
                        isRegistrationSuccessful.setValue(false);
                    }
                } else {
                    statusMessage.setValue("Login failed!");
                    isRegistrationSuccessful.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<OrangTuaModel>> call, Throwable t) {
                statusMessage.setValue("Error: " + t.getMessage());
                isRegistrationSuccessful.setValue(false);
            }
        });
    }

}
