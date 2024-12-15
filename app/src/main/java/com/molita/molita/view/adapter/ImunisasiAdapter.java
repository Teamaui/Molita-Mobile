package com.molita.molita.view.adapter;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.view.fragment.DetailImunisasiFragment;
import com.molita.molita.view.fragment.ShowEdukasiFragment;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ImunisasiAdapter extends RecyclerView.Adapter<ImunisasiAdapter.ImunisasiViewHolder>{

    private List<AnakImunisasiModel> anakImunisasiModels;
    private FragmentManager fragmentManager;

    public ImunisasiAdapter(List<AnakImunisasiModel> anakImunisasiModel, FragmentManager fragmentManager) {
        this.anakImunisasiModels = anakImunisasiModel;
        this.fragmentManager = fragmentManager;

    }

    @NonNull
    @Override
    public ImunisasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_imunisasi, parent
                , false);
        return new ImunisasiAdapter.ImunisasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImunisasiViewHolder holder, int position) {
        AnakImunisasiModel anakImunisasiModel = anakImunisasiModels.get(position);

        holder.txtNamaAnak.setText(anakImunisasiModel.getNama_anak().isEmpty() ? "-" :
                anakImunisasiModel.getNama_anak());

        holder.txtJenisImunisasi.setText(anakImunisasiModel.getNama_imunisasi() == null ? "-" :
                anakImunisasiModel.getNama_imunisasi());

        holder.txtTanggal.setText(anakImunisasiModel.getTanggal_imunisasi() == null ? "-" :
                anakImunisasiModel.getTanggal_imunisasi());

        holder.txtTempat.setText(anakImunisasiModel.getTempat_imunisasi() == null ? "-" :
                anakImunisasiModel.getTempat_imunisasi());

        String status = anakImunisasiModel.getStatus_imunisasi() == null ? "-" : anakImunisasiModel.getStatus_imunisasi();
        holder.txtStatusImunisasi.setText(status);

        // Logika untuk mengatur backgroundTint dan warna teks
        if ("Sudah".equalsIgnoreCase(status)) {
            holder.txtStatusImunisasi.setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.green))
            );
            holder.txtStatusImunisasi.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else if ("Tertunda".equalsIgnoreCase(status)) {
            holder.txtStatusImunisasi.setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.orange))
            );
            holder.txtStatusImunisasi.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        } else if ("Belum".equalsIgnoreCase(status)) {
            holder.txtStatusImunisasi.setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.red))
            );
            holder.txtStatusImunisasi.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            // Default untuk status tidak diketahui
            holder.txtStatusImunisasi.setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.gray))
            );
            holder.txtStatusImunisasi.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("idAnak", anakImunisasiModel.getId_anak());

                // Membuat fragment baru dan mengatur Bundle data
                DetailImunisasiFragment detailImunisasiFragment = new DetailImunisasiFragment();
                detailImunisasiFragment.setArguments(bundle);

                // Memulai transaksi fragment untuk mengganti fragment
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, detailImunisasiFragment); //
                // Ganti R.id
                // .fragment_container dengan ID container fragment Anda
                transaction.addToBackStack(null); // Jika Anda ingin fragment sebelumnya dapat dikembalikan
                transaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return anakImunisasiModels.size();
    }

    public void setAnakList(List<AnakImunisasiModel> newList) {
        if (newList != null && !newList.isEmpty() && newList.size() > 1) {
            Log.d("EdukasiAdapter", "New List Size: " + newList.size());
            Log.d("EdukasiAdapter222", "Old List Size: " + anakImunisasiModels.size());

            // Clear existing list and add new data
            this.anakImunisasiModels.clear();
            this.anakImunisasiModels.addAll(newList);
            notifyDataSetChanged();
        } else {
            Log.d("EdukasiAdapter", "New List is null or empty");
            this.anakImunisasiModels.clear();
            notifyDataSetChanged();
        }
    }

    public static class ImunisasiViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaAnak, txtTanggal, txtJenisImunisasi, txtStatusImunisasi, txtTempat;
        Button btnDetail;

        public ImunisasiViewHolder(View itemView) {
            super(itemView);

            txtNamaAnak = itemView.findViewById(R.id.txtNamaAnak);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtJenisImunisasi = itemView.findViewById(R.id.txtJenisImunisasi);
            txtStatusImunisasi = itemView.findViewById(R.id.txtStatus);
            txtTempat = itemView.findViewById(R.id.txtTempat);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }

    }
}
