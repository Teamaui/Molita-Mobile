package com.molita.molita.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.molita.molita.R;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.adapter.DetailImunisasiAdapter;
import com.molita.molita.view.adapter.ImunisasiAdapter;
import com.molita.molita.viewmodel.penjadwalan.PenjadwalanViewModel;

public class DetailImunisasiFragment extends Fragment {

    RecyclerView recyclerView;
    PenjadwalanViewModel penjadwalanViewModel;
    DetailImunisasiAdapter detailImunisasiAdapter;
    String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_imunisasi, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        penjadwalanViewModel = new ViewModelProvider(this).get(PenjadwalanViewModel.class);

        // Observasi data dari ViewModel
        penjadwalanViewModel.getImunisasiList().observe(getViewLifecycleOwner(), edukasiList -> {
            if (edukasiList != null) {
                // Inisialisasi adapter
                detailImunisasiAdapter = new DetailImunisasiAdapter(edukasiList);
                recyclerView.setAdapter(detailImunisasiAdapter);
            }
        });

        // Mendapatkan data dari Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("idAnak");
        }

        penjadwalanViewModel.getAnakImunisasiById(id);

        return view;
    }
}
