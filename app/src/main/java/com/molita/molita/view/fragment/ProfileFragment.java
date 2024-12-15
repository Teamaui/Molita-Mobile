package com.molita.molita.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.molita.molita.R;
import com.molita.molita.model.data.OrangTuaModel;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.auth.LoginActivity;
import com.molita.molita.view.dashboard.DashboardActivity;
import com.molita.molita.viewmodel.Profile.ProfileVIewModel;
import com.molita.molita.viewmodel.edukasi.EdukasiViewModel;

public class ProfileFragment extends Fragment {

    TextView txtUsername, txtEmail, txtNamaIbu, txtNamaAyah, txtNikIbu, txtNikAyah, txtAlamat,
            txtNomor;

    AuthRepository authRepository;
    ProfileVIewModel profileVIewModel;

    TextView txtKeluar;
    ImageView icKeluar;
    LinearLayout linearLayoutKeluar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtUsername = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtNamaIbu = view.findViewById(R.id.txtNamaIbu);
        txtNamaAyah = view.findViewById(R.id.txtNamaAyah);
        txtNikIbu = view.findViewById(R.id.txtNikIbu);
        txtNikAyah = view.findViewById(R.id.txtNikAyah);
        txtAlamat = view.findViewById(R.id.txtAlamat);
        txtNomor = view.findViewById(R.id.txtNoTelepon);

        txtKeluar = view.findViewById(R.id.txtKeluar);
        icKeluar = view.findViewById(R.id.icKeluar);
        linearLayoutKeluar = view.findViewById(R.id.linearLayoutKeluar);

        txtKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authRepository.clearAllData();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        icKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authRepository.clearAllData();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authRepository.clearAllData();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        authRepository = new AuthRepository(requireContext());

        // Inisialisasi ViewModel
        profileVIewModel = new ViewModelProvider(this).get(ProfileVIewModel.class);

        // Observasi data dari ViewModel
        profileVIewModel.getOrangTuaModel().observe(getViewLifecycleOwner(), edukasiList -> {
            if (edukasiList != null) {
                OrangTuaModel orangTuaModel= edukasiList.get(0);

                txtUsername.setText(orangTuaModel.getUsername());
                txtEmail.setText(orangTuaModel.getEmail());
                txtNamaIbu.setText(orangTuaModel.getNama_ibu());
                txtNamaAyah.setText(orangTuaModel.getNama_ayah());
                txtNikIbu.setText(orangTuaModel.getNik_ibu());
                txtNikAyah.setText(orangTuaModel.getNik_ayah());
                txtNomor.setText(orangTuaModel.getNo_telepon());
                txtAlamat.setText(orangTuaModel.getAlamat());
            }
        });


        profileVIewModel.getOrangTua(authRepository.getUserId());

        return view;
    }
}
