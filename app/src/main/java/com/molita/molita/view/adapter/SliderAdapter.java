package com.molita.molita.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.molita.molita.view.fragment.slider.SlideSecondFragment;
import com.molita.molita.view.fragment.slider.SlideThirdFragment;
import com.molita.molita.view.fragment.slider.SliderFirstFragment;

public class SliderAdapter extends FragmentStateAdapter {

    public SliderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SliderFirstFragment();
            case 1:
                return new SlideSecondFragment();
            case 2:
                return new SlideThirdFragment();
            default:
                return new SliderFirstFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Jumlah halaman slider
    }
}
