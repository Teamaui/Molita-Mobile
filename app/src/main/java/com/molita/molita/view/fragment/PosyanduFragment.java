package com.molita.molita.view.fragment;

import android.os.Bundle;
import android.util.Log;
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

import com.molita.molita.R;
import com.molita.molita.model.data.PosyanduModel;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.adapter.PosyanduAdapter;
import com.molita.molita.viewmodel.penjadwalan.PenjadwalanViewModel;

import java.util.ArrayList;
import java.util.List;

public class PosyanduFragment extends Fragment {

    RecyclerView recyclerView;
    PosyanduAdapter posyanduAdapter;
    PenjadwalanViewModel penjadwalanViewModel;
    AuthRepository authRepository;
    TextView txtNamaPos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posyandu, container, false);

        recyclerView = view.findViewById(R.id.recyclerView1);
        txtNamaPos = view.findViewById(R.id.txtNamaPos);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        authRepository = new AuthRepository(requireContext());

        penjadwalanViewModel = new ViewModelProvider(this).get(PenjadwalanViewModel.class);
        penjadwalanViewModel.getPosyanduList().observe(getViewLifecycleOwner(), new Observer<List<PosyanduModel>>() {
            @Override
            public void onChanged(List<PosyanduModel> posyanduModels) {
                // Pastikan data yang diterima tidak null atau kosong
                if (posyanduModels != null && !posyanduModels.isEmpty()) {
                    // Set adapter dengan data yang ada
                    posyanduAdapter = new PosyanduAdapter(posyanduModels);
                    recyclerView.setAdapter(posyanduAdapter);
                    txtNamaPos.setText(posyanduModels.get(0).getPos());
                } else {
                    // Menangani jika data kosong atau null
                    Log.d("POSYANDU", "Data Posyandu kosong");
                }
            }
        });

        penjadwalanViewModel.getPosyandu(authRepository.getUserId());

        return view;
    }
}
