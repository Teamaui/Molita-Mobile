package com.molita.molita.viewmodel.Pertumbuhan;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.molita.molita.model.data.AnakModel;
import com.molita.molita.model.data.PertumbuhanModel;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.model.repository.AnakRepository;
import com.molita.molita.model.repository.PertumbuhanRepository;
import com.molita.molita.view.fragment.GrafikFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PertumbuhanViewModel extends AndroidViewModel {

    PertumbuhanRepository pertumbuhanRepository;
    AnakRepository anakRepository;

    private MutableLiveData<List<AnakModel>> anakList = new MutableLiveData<>();
    private MutableLiveData<List<PertumbuhanModel>> pertumbuhanList = new MutableLiveData<>();

    public PertumbuhanViewModel(@NonNull Application application) {
        super(application);
        pertumbuhanRepository = new PertumbuhanRepository(application.getApplicationContext());
        anakRepository = new AnakRepository();
    }

    public LiveData<List<PertumbuhanModel>> getPertumbuhanList() {
        return pertumbuhanList;
    }

    public MutableLiveData<List<AnakModel>> getAnakList() {
        return anakList;
    }

    public void setAnakList(MutableLiveData<List<AnakModel>> anakList) {
        this.anakList = anakList;
    }

    public void getPertumbuhan() {
        this.pertumbuhanRepository.getPertumbuhan().enqueue(new Callback<ResponseListData<PertumbuhanModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<PertumbuhanModel>> call, Response<ResponseListData<PertumbuhanModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    pertumbuhanList.setValue(responseListData.getData());
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<PertumbuhanModel>> call, Throwable t) {

            }
        });
    }

    public void getPertumbuhanByAnak(String id) {
        this.pertumbuhanRepository.getPertumbuhanById(id).enqueue(new Callback<ResponseListData<PertumbuhanModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<PertumbuhanModel>> call, Response<ResponseListData<PertumbuhanModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    pertumbuhanList.setValue(responseListData.getData());
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<PertumbuhanModel>> call, Throwable t) {

            }
        });
    }

    public void getAnak(String id) {
        anakRepository.getAnakOrangTua(id).enqueue(new Callback<ResponseListData<AnakModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<AnakModel>> call, Response<ResponseListData<AnakModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    anakList.postValue(responseListData.getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<AnakModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}


