package com.molita.molita.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.adapter.ImunisasiAdapter;
import com.molita.molita.viewmodel.penjadwalan.PenjadwalanViewModel;

public class ImunisasiFragment extends Fragment {

    RecyclerView recyclerView;
    ImunisasiAdapter imunisasiAdapter;
    PenjadwalanViewModel penjadwalanViewModel;
    AuthRepository authRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imunisasi, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        authRepository = new AuthRepository(requireContext());

        penjadwalanViewModel = new ViewModelProvider(this).get(PenjadwalanViewModel.class);

        // Observasi data dari ViewModel
        penjadwalanViewModel.getAnakImunisasiList().observe(getViewLifecycleOwner(), edukasiList -> {
            if (edukasiList != null) {
                // Inisialisasi adapter
                imunisasiAdapter = new ImunisasiAdapter(edukasiList, getParentFragmentManager());
                recyclerView.setAdapter(imunisasiAdapter);
            }
        });

        penjadwalanViewModel.getAnakImunisasi(authRepository.getUserId());

        return view;
    }

}
