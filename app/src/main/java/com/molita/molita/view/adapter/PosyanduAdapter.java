package com.molita.molita.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.model.data.PosyanduModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class PosyanduAdapter extends RecyclerView.Adapter<PosyanduAdapter.PosyanduViewHolder> {

    private List<PosyanduModel> posyanduModels;

    public PosyanduAdapter(List<PosyanduModel> posyanduModel) {
        this.posyanduModels = posyanduModel != null ? posyanduModel : new ArrayList<PosyanduModel>(); //
        // Menghindari null pointer
    }

    @NonNull
    @Override
    public PosyanduViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_posyandu, parent, false);
        return new PosyanduViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosyanduViewHolder holder, int position) {
        // Mengambil model pada posisi tertentu
        PosyanduModel posyanduModel = posyanduModels.get(position);

        String tanggal = posyanduModel.getTanggal() != null ? posyanduModel.getTanggal() : "-";
        if (posyanduModel.getTanggal() != null) {
            tanggal = formatDate(tanggal);
        }

        String jamMulai = posyanduModel.getJam_mulai() != null ? posyanduModel.getJam_mulai() : "-";
        if (posyanduModel.getJam_mulai() != null) {
            jamMulai = formatTime(jamMulai);
        }

        String jamSelesai = posyanduModel.getJam_mulai() != null ? posyanduModel.getJam_mulai() :
                "-";
        if (posyanduModel.getJam_mulai() != null) {
            jamSelesai = formatTime(jamSelesai);
        }

        // Menangani null atau data kosong dengan nilai default
        holder.txtTanggal.setText(tanggal);
        holder.txtJamMulai.setText(jamMulai);
        holder.txtJamSelesai.setText(jamSelesai);
    }

    @Override
    public int getItemCount() {
        return posyanduModels != null ? posyanduModels.size() : 0; // Menghindari NullPointerException
    }

    public static String formatDate(String dateString) {
        // Format input yang diberikan
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format output yang diinginkan
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");

        try {
            // Parsing tanggal string ke objek Date
            Date date = inputFormat.parse(dateString);

            // Mengubah objek Date menjadi string dengan format yang baru
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;  // Jika terjadi kesalahan, kembalikan string aslinya
        }
    }

    public static String formatTime(String timeString) {
        // Format input waktu yang diberikan
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");

        // Format output waktu yang diinginkan
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");

        try {
            // Parsing waktu string ke objek Date
            Date time = inputFormat.parse(timeString);

            // Mengubah objek Date menjadi string dengan format yang baru
            return outputFormat.format(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return timeString;  // Jika terjadi kesalahan, kembalikan string aslinya
        }
    }

    public static class PosyanduViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtJamMulai, txtJamSelesai;

        public PosyanduViewHolder(View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtJamMulai = itemView.findViewById(R.id.txtJamMulai);
            txtJamSelesai = itemView.findViewById(R.id.txtJamSelesai);

        }
    }
}
