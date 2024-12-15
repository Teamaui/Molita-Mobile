package com.molita.molita.viewmodel.penjadwalan;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.model.data.ImunisasiModel;
import com.molita.molita.model.data.PosyanduModel;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.model.repository.PenjadwalanRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjadwalanViewModel extends AndroidViewModel {

    PenjadwalanRepository penjadwalanRepository;

    private MutableLiveData<List<AnakImunisasiModel>> anakImunisasiList = new MutableLiveData<>();
    private MutableLiveData<List<PosyanduModel>> posyanduList = new MutableLiveData<>();
    private MutableLiveData<List<ImunisasiModel>> imunisasiList = new MutableLiveData<>();

    public PenjadwalanViewModel(@NonNull Application application) {
        super(application);
        penjadwalanRepository = new PenjadwalanRepository();
    }

    public MutableLiveData<List<PosyanduModel>> getPosyanduList() {
        return posyanduList;
    }

    public void setPosyanduList(MutableLiveData<List<PosyanduModel>> posyanduList) {
        this.posyanduList = posyanduList;
    }

    public MutableLiveData<List<AnakImunisasiModel>> getAnakImunisasiList() {
        return anakImunisasiList;
    }

    public void setAnakImunisasiList(MutableLiveData<List<AnakImunisasiModel>> anakImunisasiList) {
        this.anakImunisasiList = anakImunisasiList;
    }

    public MutableLiveData<List<ImunisasiModel>> getImunisasiList() {
        return imunisasiList;
    }

    public void setImunisasiList(MutableLiveData<List<ImunisasiModel>> imunisasiList) {
        this.imunisasiList = imunisasiList;
    }

    public void getAnakImunisasi(String id) {
        this.penjadwalanRepository.getAnakImunisasi(id).enqueue(new Callback<ResponseListData<AnakImunisasiModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<AnakImunisasiModel>> call, Response<ResponseListData<AnakImunisasiModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    anakImunisasiList.setValue(responseListData.getData());
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<AnakImunisasiModel>> call, Throwable t) {

            }
        });
    }

    public void getAnakImunisasiById(String id) {
        this.penjadwalanRepository.getAnakImunisasiById(id).enqueue(new Callback<ResponseListData<ImunisasiModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<ImunisasiModel>> call, Response<ResponseListData<ImunisasiModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    imunisasiList.setValue(responseListData.getData());
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<ImunisasiModel>> call, Throwable t) {

            }
        });
    }

    public void getPosyandu(String id) {
        this.penjadwalanRepository.getAnakPosyandu(id).enqueue(new Callback<ResponseListData<PosyanduModel>>() {
            @Override
            public void onResponse(Call<ResponseListData<PosyanduModel>> call, Response<ResponseListData<PosyanduModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseListData responseListData = response.body();
                    posyanduList.setValue(responseListData.getData());
                    Log.d("PESAN-L", "Gagal mengambil data");
                } else {
                    Log.d("PESAN-L", "Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<PosyanduModel>> call, Throwable t) {

            }
        });
    }
}
