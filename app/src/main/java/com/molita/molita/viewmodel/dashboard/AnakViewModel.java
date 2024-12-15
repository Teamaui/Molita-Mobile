package com.molita.molita.viewmodel.dashboard;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.molita.molita.model.data.AnakModel;
import com.molita.molita.model.data.DataAnakModel;
import com.molita.molita.model.data.PertumbuhanModel;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.model.repository.AnakRepository;
import com.molita.molita.model.repository.PertumbuhanRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnakViewModel extends AndroidViewModel {

    AnakRepository anakRepository;

    private MutableLiveData<List<DataAnakModel>> anakList = new MutableLiveData<>();

    public AnakViewModel(@NonNull Application application) {
        super(application);
        anakRepository = new AnakRepository();
    }

    public MutableLiveData<List<DataAnakModel>> getAnakList() {
        return anakList;
    }

    public void setAnakList(MutableLiveData<List<DataAnakModel>> anakList) {
        this.anakList = anakList;
    }

    public void getAllDataAnakById(String id) {
        anakRepository.getAllDataAnakById(id).enqueue(new Callback<ResponseListData<DataAnakModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<DataAnakModel>> call, Response<ResponseListData<DataAnakModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    anakList.setValue(responseListData.getData());
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<DataAnakModel>> call, Throwable t) {

            }
        });
    }
}


