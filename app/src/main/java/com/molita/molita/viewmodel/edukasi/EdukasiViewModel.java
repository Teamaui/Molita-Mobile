package com.molita.molita.viewmodel.edukasi;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.molita.molita.model.data.EdukasiModel;
import com.molita.molita.model.data.JenisEdukasiModel;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.model.repository.EdukasiRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EdukasiViewModel extends AndroidViewModel {

    private EdukasiRepository edukasiRepository;
    private MutableLiveData<String> statusMessage = new MutableLiveData<>();
    private MutableLiveData<List<EdukasiModel>> modelList = new MutableLiveData<>();
    private MutableLiveData<String> token = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRegistrationSuccessful = new MutableLiveData<>();
    private Context context;
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<List<JenisEdukasiModel>> edukasiList = new MutableLiveData<>();


    public EdukasiViewModel(@NotNull Application application) {
        super(application);
        edukasiRepository = new EdukasiRepository();
        this.context = application.getApplicationContext();
    }

    public LiveData<List<JenisEdukasiModel>> getEdukasiList() {
        return edukasiList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<EdukasiModel>> getModelList() {
        return modelList;
    }

    public void setModelList(List<EdukasiModel> modelList) {
        this.modelList.setValue(modelList);
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

    public void edukasi() {
        this.edukasiRepository.edukasi().enqueue(new Callback<ResponseListData<EdukasiModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<EdukasiModel>> call, Response<ResponseListData<EdukasiModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    modelList.setValue(responseListData.getData());
                } else {
                    Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<EdukasiModel>> call, Throwable t) {

            }
        });
    }

    public void jenisEdukasi() {
        this.edukasiRepository.jenisEdukasi().enqueue(new Callback<ResponseListData<JenisEdukasiModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<JenisEdukasiModel>> call, Response<ResponseListData<JenisEdukasiModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    edukasiList.setValue(responseListData.getData());
                } else {
                    Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<JenisEdukasiModel>> call, Throwable t) {

            }
        });
    }

    public void selectEdukasiBySlug(String slug) {
        this.edukasiRepository.getEdukasiBySlug(slug).enqueue(new Callback<ResponseListData<EdukasiModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<EdukasiModel>> call, Response<ResponseListData<EdukasiModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    modelList.setValue(responseListData.getData());
                } else {
                    Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<EdukasiModel>> call, Throwable t) {

            }
        });
    }

}
