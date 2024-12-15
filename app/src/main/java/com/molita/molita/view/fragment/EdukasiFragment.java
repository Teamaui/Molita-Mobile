package com.molita.molita.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.molita.molita.R;
import com.molita.molita.model.data.JenisEdukasiModel;
import com.molita.molita.view.adapter.EdukasiAdapter;
import com.molita.molita.viewmodel.edukasi.EdukasiViewModel;

import java.util.ArrayList;
import java.util.List;

public class EdukasiFragment extends Fragment {

    EdukasiAdapter edukasiAdapter;
    RecyclerView recyclerView;
    EdukasiViewModel edukasiViewModel;
    ChipGroup chipGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edukasi, container, false);

        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Inisialisasi adapter
        edukasiAdapter = new EdukasiAdapter(requireContext(), new ArrayList<>(), getParentFragmentManager());
        recyclerView.setAdapter(edukasiAdapter);

        // Inisialisasi ViewModel
        edukasiViewModel = new ViewModelProvider(this).get(EdukasiViewModel.class);

        // Observasi data dari ViewModel
        edukasiViewModel.getModelList().observe(getViewLifecycleOwner(), edukasiList -> {
            if (edukasiList != null) {
                edukasiAdapter.setEdukasiList(edukasiList);
            }
        });

        // Observasi pesan error
        edukasiViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        chipGroup = view.findViewById(R.id.chipGroup);

        edukasiViewModel.getEdukasiList().observe(getViewLifecycleOwner(), edukasis -> {
            // Menambahkan Chip berdasarkan data yang diterima
            if (edukasis != null) {
                addChips(edukasis);
            }
        });

        // Ambil data edukasi dari API (atau sumber lainnya)
        edukasiViewModel.edukasi();
        edukasiViewModel.jenisEdukasi();

        return view;
    }

    private void addChips(List<JenisEdukasiModel> edukasis) {
        chipGroup.removeAllViews();  // Bersihkan ChipGroup sebelum menambahkan chip baru

        for (JenisEdukasiModel edukasi : edukasis) {
            Chip chip = new Chip(requireContext());
            chip.setText(edukasi.getNama_edukasi());
            chip.setCheckable(true);
            chip.setClickable(true);

            // Set listener untuk memilih chip
            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Pilih edukasi saat chip diklik
                    edukasiViewModel.selectEdukasiBySlug(edukasi.getSlug());

                    // Uncheck semua chip lainnya
                    uncheckOtherChips(chip);
                }
            });

            chipGroup.addView(chip);
        }
    }

    // Fungsi untuk memastikan hanya satu chip yang terpilih
    private void uncheckOtherChips(Chip selectedChip) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            // Hanya uncheck chip yang bukan yang terpilih
            if (chip != selectedChip) {
                chip.setChecked(false);
            }
        }
    }

}
