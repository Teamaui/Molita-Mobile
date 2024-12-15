package com.molita.molita.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.molita.molita.R;

public class ShowEdukasiFragment extends Fragment {

    TextView judulEdukasi, deskripsiEdukasi, txtTanggalWaktu;
    ImageView imgEdukasi;
    ImageButton btnBack;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_edukasi, container, false);

        this.judulEdukasi = view.findViewById(R.id.judulTeks);
        this.deskripsiEdukasi = view.findViewById(R.id.deskripsiTeks);
        this.imgEdukasi = view.findViewById(R.id.imgEdukasi);
        this.txtTanggalWaktu = view.findViewById(R.id.txtTanggalWaktu);
        this.btnBack = view.findViewById(R.id.back);


        // Mendapatkan data dari Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String imgUrl = bundle.getString("imgUrlEdukasi");
            String judul = bundle.getString("judulEdukasi");
            String tanggal = bundle.getString("tanggalWaktu");
            String deskripsi = bundle.getString("deskripsiEdukasi");

            Glide.with(this).load(imgUrl).into(imgEdukasi);
            judulEdukasi.setText(judul);
            txtTanggalWaktu.setText(tanggal);
            deskripsiEdukasi.setText(Html.fromHtml(deskripsi, Html.FROM_HTML_MODE_COMPACT));
        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat fragment baru (EdukasiFragment) untuk kembali
                EdukasiFragment edukasiFragment = new EdukasiFragment();
                edukasiFragment.setArguments(bundle); // Menambahkan data ke fragment jika diperlukan

                // Memulai transaksi fragment untuk mengganti fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, edukasiFragment); // Ganti dengan instance EdukasiFragment
                transaction.addToBackStack(null); // Menambahkan transaksi ke back stack agar bisa kembali ke fragment sebelumnya
                transaction.commit();
            }
        });

        return view;
    }
}
