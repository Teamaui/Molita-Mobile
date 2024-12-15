package com.molita.molita.view.fragment;

import static com.molita.molita.view.adapter.PosyanduAdapter.formatDate;
import static com.molita.molita.view.adapter.PosyanduAdapter.formatTime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.molita.molita.R;
import com.molita.molita.model.data.DataAnakModel;
import com.molita.molita.model.data.PosyanduModel;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.model.repository.PenjadwalanRepository;
import com.molita.molita.view.adapter.DaftarAnakAdapter;
import com.molita.molita.view.adapter.EdukasiAdapter;
import com.molita.molita.viewmodel.Pertumbuhan.PertumbuhanViewModel;
import com.molita.molita.viewmodel.dashboard.AnakViewModel;
import com.molita.molita.viewmodel.edukasi.EdukasiViewModel;
import com.molita.molita.viewmodel.penjadwalan.PenjadwalanViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    TextView txxNama, txtNamaPos, txtTanggal, txtJamMulai, txtJamSelesai;
    RecyclerView recyclerViewDaftarAnak, recyclerViewEdukasi;
    AuthRepository authRepository;
    PenjadwalanViewModel penjadwalanViewModel;
    AnakViewModel anakViewModel;
    DaftarAnakAdapter daftarAnakAdapter;
    EdukasiAdapter edukasiAdapter;
    EdukasiViewModel edukasiViewModel;
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        txxNama = view.findViewById(R.id.txtNama);
        txtNamaPos = view.findViewById(R.id.txtNamaPos);
        txtTanggal = view.findViewById(R.id.txtTanggal);
        txtJamMulai = view.findViewById(R.id.txtJamMulai);
        txtJamSelesai = view.findViewById(R.id.txtJamSelesai);

        recyclerViewDaftarAnak = view.findViewById(R.id.recyclerViewDaftarAnak);
        recyclerViewDaftarAnak.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));

        daftarAnakAdapter = new DaftarAnakAdapter(new ArrayList<>());
        recyclerViewDaftarAnak.setAdapter(daftarAnakAdapter);

        recyclerViewEdukasi = view.findViewById(R.id.recyclerViewEdukasi);
        recyclerViewEdukasi.setLayoutManager(new LinearLayoutManager(requireContext()));

        edukasiAdapter = new EdukasiAdapter(requireContext(), new ArrayList<>(), getParentFragmentManager());
        recyclerViewEdukasi.setAdapter(edukasiAdapter);

        authRepository = new AuthRepository(requireContext());
        txxNama.setText(authRepository.getUserNameIbu());

        penjadwalanViewModel = new ViewModelProvider(this).get(PenjadwalanViewModel.class);
        penjadwalanViewModel.getPosyanduList().observe(getViewLifecycleOwner(), new Observer<List<PosyanduModel>>() {
            @Override
            public void onChanged(List<PosyanduModel> posyanduModels) {
                if (posyanduModels != null && !posyanduModels.isEmpty()) {
                    PosyanduModel posyanduModel = posyanduModels.get(0);

                    String tanggal = posyanduModel.getTanggal() != null ? posyanduModel.getTanggal() : "-";
                    if (posyanduModel.getTanggal() != null) {
                        tanggal = formatDate(tanggal);
                    }

                    String jamMulai = posyanduModel.getJam_mulai() != null ? posyanduModel.getJam_mulai() : "-";
                    if (posyanduModel.getJam_mulai() != null) {
                        jamMulai = formatTime(jamMulai);
                    }

                    String jamSelesai = posyanduModel.getJam_mulai() != null ? posyanduModel.getJam_mulai() :
                            "-";
                    if (posyanduModel.getJam_mulai() != null) {
                        jamSelesai = formatTime(jamSelesai);
                    }

                    txtNamaPos.setText(posyanduModel.getPos() != null ?
                            posyanduModel.getPos() : "-");
                    txtTanggal.setText(tanggal);
                    txtJamMulai.setText(jamMulai);
                    txtJamSelesai.setText(jamSelesai);
                }
            }
        });

        penjadwalanViewModel.getPosyandu(authRepository.getUserId());

        anakViewModel = new ViewModelProvider(this).get(AnakViewModel.class);

        anakViewModel.getAnakList().observe(getViewLifecycleOwner(), new Observer<List<DataAnakModel>>() {
            @Override
            public void onChanged(List<DataAnakModel> dataAnakModelList) {
                daftarAnakAdapter = new DaftarAnakAdapter(dataAnakModelList);
                recyclerViewDaftarAnak.setAdapter(daftarAnakAdapter);
            }
        });

        anakViewModel.getAllDataAnakById(authRepository.getUserId());

        // Inisialisasi ViewModel
        edukasiViewModel = new ViewModelProvider(this).get(EdukasiViewModel.class);

        // Observasi data dari ViewModel
        edukasiViewModel.getModelList().observe(getViewLifecycleOwner(), edukasiList -> {
            if (edukasiList != null) {
                edukasiAdapter.setEdukasiList(edukasiList);
            }
        });

        edukasiViewModel.edukasi();

        return view;
    }
}
