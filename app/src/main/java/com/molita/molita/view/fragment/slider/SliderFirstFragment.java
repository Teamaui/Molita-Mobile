package com.molita.molita.view.fragment.slider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.molita.molita.R;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.dashboard.DashboardActivity;

public class SliderFirstFragment extends Fragment {

    AuthRepository authRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        authRepository = new AuthRepository(requireContext());  // Menggunakan requireContext() untuk mendapatkan konteks yang valid

        if (authRepository.getUserId() != null) {
            Intent intent = new Intent(requireActivity(), DashboardActivity.class);
            startActivity(intent);

            requireActivity().finish();
        }


        return inflater.inflate(R.layout.fragment_slide_first, container, false);
    }
}
