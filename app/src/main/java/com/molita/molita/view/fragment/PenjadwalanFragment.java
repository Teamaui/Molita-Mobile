package com.molita.molita.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.molita.molita.R;
import com.molita.molita.view.adapter.ViewPagerAdapter;
import com.molita.molita.view.fragment.tablayout.BeratBadanFragment;
import com.molita.molita.view.fragment.tablayout.SemuaPetumbuhanFragment;
import com.molita.molita.view.fragment.tablayout.TinggiBadanFragment;

public class PenjadwalanFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penjadwalan, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        Fragment[] fragments = new Fragment[] {
                new ImunisasiFragment(),
                new PosyanduFragment()
        };

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(requireActivity(), fragments);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Imunisasi");
                        break;
                    case 1:
                        tab.setText("Posyandu");
                        break;
                }
            }
        }).attach();

        return view;
    }
}
