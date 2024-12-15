package com.molita.molita.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.molita.molita.R;
import com.molita.molita.model.data.AnakModel;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.model.repository.PertumbuhanRepository;
import com.molita.molita.view.adapter.MyFragmentStateAdapter;
import com.molita.molita.view.adapter.ViewPagerAdapter;
import com.molita.molita.view.fragment.tablayout.BeratBadanFragment;
import com.molita.molita.view.fragment.tablayout.SemuaPetumbuhanFragment;
import com.molita.molita.view.fragment.tablayout.TinggiBadanFragment;
import com.molita.molita.viewmodel.Pertumbuhan.PertumbuhanViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GrafikFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    Spinner spinner;
    PertumbuhanViewModel pertumbuhanViewModel;
    PertumbuhanRepository pertumbuhanRepository;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> tabTitles = new ArrayList<>();
    String idAnak;
    AuthRepository authRepository;
    int umurAnak;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grafik, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        spinner = view.findViewById(R.id.spinner);
        pertumbuhanViewModel = new ViewModelProvider(this).get(PertumbuhanViewModel.class);
        pertumbuhanRepository = new PertumbuhanRepository(requireContext());
        authRepository = new AuthRepository(requireContext());

        // Pantau data anak dari ViewModel
        pertumbuhanViewModel.getAnakList().observe(getViewLifecycleOwner(), anakList -> {
            ArrayAdapter<AnakModel> adapter = new ArrayAdapter<>(
                    requireContext(),
                    R.layout.spinner_item,
                    anakList
            );
            adapter.setDropDownViewResource(R.layout.spinner_item_card);
            idAnak = anakList.get(0).getIdAnak();
            umurAnak = GrafikFragment.calculateAge(anakList.get(0).getTanggalLahir());
            spinner.setAdapter(adapter);
        });

        // Panggil data anak dari API
        pertumbuhanViewModel.getAnak(authRepository.getUserId());

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SemuaPetumbuhanFragment(idAnak, umurAnak));
        fragmentList.add(new BeratBadanFragment(idAnak, umurAnak));
        fragmentList.add(new TinggiBadanFragment(idAnak, umurAnak));

        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getActivity(), fragmentList);
        viewPager.setAdapter(adapter);

        // Konfigurasi TabLayout dengan ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Semua");
                    break;
                case 1:
                    tab.setText("Berat Badan");
                    break;
                case 2:
                    tab.setText("Tinggi Badan");
                    break;
            }
        }).attach();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Ambil id_anak dari item yang dipilih
                AnakModel selectedAnak = (AnakModel) parentView.getItemAtPosition(position);
                String idAnak = selectedAnak.getIdAnak();

                System.out.println(idAnak);

                // Ganti fragment sesuai dengan tab yang aktif
                // Perbarui fragmentList sesuai dengan idAnak yang dipilih
                fragmentList.clear();
                fragmentList.add(new SemuaPetumbuhanFragment(idAnak, umurAnak));
                fragmentList.add(new BeratBadanFragment(idAnak, umurAnak));
                fragmentList.add(new TinggiBadanFragment(idAnak, umurAnak));

                tabTitles.clear();
                tabTitles.add("Semua");
                tabTitles.add("Berat Badan");
                tabTitles.add("Tinggi Badan");

                // Memanggil setupViewPagerAndTabs untuk memperbarui tab dan fragment
                setupViewPagerAndTabs(viewPager, tabLayout, fragmentList, tabTitles);

                // Memperbarui fragment di adapter
                MyFragmentStateAdapter adapter = (MyFragmentStateAdapter) viewPager.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();  // Memperbarui data fragment pada adapter
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle jika tidak ada yang dipilih
            }
        });

        return  view;
    }

    public void setupViewPagerAndTabs(ViewPager2 viewPager, TabLayout tabLayout, List<Fragment> fragmentList, List<String> tabTitles) {
        // Membuat adapter dan set ke viewPager
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getActivity(), fragmentList);
        viewPager.setAdapter(adapter);

        // Mengonfigurasi TabLayout dengan ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position < tabTitles.size()) {
                tab.setText(tabTitles.get(position));
            }
        }).attach();

        // Memberi tahu adapter bahwa data telah berubah untuk di-refresh
        adapter.notifyDataSetChanged();
    }

    public static String interpretZScore(double zScore, String indicator) {
        switch (indicator) {
            case "TB/U": // Tinggi Badan per Umur
                if (zScore < -3) {
                    return "Severely stunted (sangat pendek). Anak Anda memiliki tinggi badan yang sangat jauh di bawah rata-rata untuk usianya. Ini dapat menjadi tanda masalah kronis dalam pertumbuhan dan perlu konsultasi segera dengan dokter.";
                } else if (zScore < -2) {
                    return "Stunted (pendek). Anak Anda memiliki tinggi badan di bawah rata-rata untuk usianya. Perlu diperhatikan asupan nutrisi dan kesehatannya.";
                } else {
                    return "Normal. Tinggi badan anak Anda sesuai dengan rata-rata untuk usianya.";
                }

            case "BB/TB": // Berat Badan per Tinggi Badan
                if (zScore < -3) {
                    return "Severely wasted (sangat kurang). Berat badan anak Anda sangat rendah dibandingkan tinggi badannya. Ini merupakan tanda gizi buruk akut yang membutuhkan perhatian segera.";
                } else if (zScore < -2) {
                    return "Wasted (kurang). Berat badan anak Anda di bawah rata-rata untuk tinggi badannya. Perlu diperhatikan pola makan dan kesehatannya.";
                } else if (zScore > 2) {
                    return "Overweight (berat badan berlebih). Berat badan anak Anda lebih tinggi dibandingkan tinggi badannya. Perhatikan pola makan dan aktivitas fisiknya.";
                } else {
                    return "Normal. Berat badan anak Anda sesuai dengan tinggi badannya.";
                }

            case "LK/U": // Lingkar Kepala per Umur
                if (zScore < -2) {
                    return "Microcephaly (lingkar kepala kecil). Lingkar kepala anak Anda lebih kecil dari rata-rata untuk usianya. Hal ini bisa berkaitan dengan perkembangan otak, dan sebaiknya konsultasikan dengan dokter.";
                } else if (zScore > 2) {
                    return "Macrocephaly (lingkar kepala besar). Lingkar kepala anak Anda lebih besar dari rata-rata untuk usianya. Ini dapat menjadi tanda kondisi tertentu yang memerlukan evaluasi medis.";
                } else {
                    return "Normal. Lingkar kepala anak Anda sesuai dengan rata-rata untuk usianya.";
                }

            default:
                return "Indikator tidak diketahui. Pastikan Anda menggunakan indikator yang benar seperti 'TB/U', 'BB/TB', atau 'LK/U'.";
        }
    }

    public static int calculateAge(String birthDateString) {
        try {
            // Format inputan tanggal lahir "YYYY-MM-DD"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(birthDateString);

            // Mendapatkan tanggal sekarang
            Calendar currentDate = Calendar.getInstance();

            // Menggunakan Calendar untuk mendeteksi tahun, bulan, dan hari dari tanggal lahir dan tanggal sekarang
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthDate);

            int age = currentDate.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

            // Mengatur perhitungan jika tanggal lahir belum tercapai di tahun ini
            if (currentDate.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                    (currentDate.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                            currentDate.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            return age;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;  // Mengembalikan nilai negatif jika terjadi kesalahan
        }
    }

}

