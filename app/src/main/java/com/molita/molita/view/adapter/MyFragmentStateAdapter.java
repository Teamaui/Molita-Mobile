package com.molita.molita.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;

    public MyFragmentStateAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragmentList = fragments;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);  // Mengembalikan fragment berdasarkan posisi
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();  // Mengembalikan jumlah fragment
    }

    // Metode untuk mengganti fragment di dalam list
    public void updateFragmentList(List<Fragment> newFragmentList) {
        fragmentList = newFragmentList;
        notifyDataSetChanged();  // Memberitahu adapter bahwa data telah berubah
    }
}
