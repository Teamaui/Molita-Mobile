package com.molita.molita.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.model.data.DataAnakModel;

import java.util.List;

public class DaftarAnakAdapter extends RecyclerView.Adapter<DaftarAnakAdapter.DaftarAnakViewHolder> {

    List<DataAnakModel> dataAnakModelList;

    public DaftarAnakAdapter(List<DataAnakModel> dataAnakModelList) {
        this.dataAnakModelList = dataAnakModelList;
    }

    @NonNull
    @Override
    public DaftarAnakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_daftar_anak, parent
                , false);
        return new DaftarAnakAdapter.DaftarAnakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarAnakViewHolder holder, int position) {
        DataAnakModel dataAnakModel = dataAnakModelList.get(position);

        holder.txtNamaAnak.setText(dataAnakModel.getNama_anak() == null ? "-" :
                dataAnakModel.getNama_anak());
        holder.txtImunisasi.setText(dataAnakModel.getNama_imunisasi() == null ? "-" :
                dataAnakModel.getNama_imunisasi());
        holder.txtTanggal.setText(dataAnakModel.getTanggal_lahir() == null ? "-" :
                dataAnakModel.getTanggal_lahir());
        holder.txtBerat.setText(dataAnakModel.getBerat_badan() == 0 ? "-" :
                String.valueOf(dataAnakModel.getBerat_badan()));
        holder.txtTinggi.setText(dataAnakModel.getTinggi_badan() == 0 ? "-" :
                String.valueOf(dataAnakModel.getTinggi_badan()));
        holder.txtLingkar.setText(dataAnakModel.getLingkar_kepala() == 0 ? "-" :
                String.valueOf(dataAnakModel.getLingkar_kepala()));

    }

    @Override
    public int getItemCount() {
        return dataAnakModelList.size();
    }

    public static class DaftarAnakViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaAnak, txtTanggal, txtImunisasi, txtBerat, txtTinggi, txtLingkar;

        public DaftarAnakViewHolder(View view) {
            super(view);
            txtNamaAnak = view.findViewById(R.id.txtNamaAnak);
            txtTanggal = view.findViewById(R.id.txtTanggal);
            txtImunisasi = view.findViewById(R.id.txtImunisasi);
            txtBerat = view.findViewById(R.id.txtBerat);
            txtTinggi = view.findViewById(R.id.txtTinggi);
            txtLingkar = view.findViewById(R.id.txtLingkar);
        }


    }

}
