package com.molita.molita.view.adapter;

import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.model.data.ImunisasiModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailImunisasiAdapter extends RecyclerView.Adapter<DetailImunisasiAdapter.ImunisasiViewHolder>{

    private List<ImunisasiModel> imunisasiModels;

    public DetailImunisasiAdapter(List<ImunisasiModel> imunisasiModels) {
        this.imunisasiModels = imunisasiModels;
    }

    @NonNull
    @Override
    public ImunisasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_detail_imunisasi, parent
                , false);
        return new DetailImunisasiAdapter.ImunisasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImunisasiViewHolder holder, int position) {

        ImunisasiModel imunisasiModel = imunisasiModels.get(position);

        holder.txtJenisImunisasi.setText(imunisasiModel.getNama_imunisasi() == null ? "-" :
                imunisasiModel.getNama_imunisasi());


        String tanggal = imunisasiModel.getTanggal_imunisasi() == null ? "-" :
                imunisasiModel.getTanggal_imunisasi();

        if(imunisasiModel.getTanggal_imunisasi() != null) {
            tanggal = formatDate(tanggal);
        }

        holder.txtTanggal.setText(tanggal);

        holder.txtTempat.setText(imunisasiModel.getTempat_imunisasi() == null ? "-" :
                imunisasiModel.getTempat_imunisasi());

        String status = imunisasiModel.getStatus_imunisasi() == null ? "-" :
                imunisasiModel.getStatus_imunisasi();
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


    @Override
    public int getItemCount() {
        return imunisasiModels.size();
    }

    public static class ImunisasiViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtJenisImunisasi, txtStatusImunisasi, txtTempat;

        public ImunisasiViewHolder(View itemView) {
            super(itemView);

            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtJenisImunisasi = itemView.findViewById(R.id.txtJenisImunisasi);
            txtStatusImunisasi = itemView.findViewById(R.id.txtStatus);
            txtTempat = itemView.findViewById(R.id.txtTempat);
        }

    }
}
