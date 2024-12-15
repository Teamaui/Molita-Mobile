package com.molita.molita.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.molita.molita.R;
import com.molita.molita.model.api.ApiService;
import com.molita.molita.model.data.BeritaModel;
import com.molita.molita.model.data.EdukasiModel;
import com.molita.molita.model.data.ResponseData;
import com.molita.molita.model.data.ResponseListData;
import com.molita.molita.view.fragment.ShowEdukasiFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EdukasiAdapter extends RecyclerView.Adapter<EdukasiAdapter.EdukasiViewHolder> {

    private Context context;
    private List<EdukasiModel> edukasiModels;
    private FragmentManager fragmentManager;

    public EdukasiAdapter(Context context, List<EdukasiModel> edukasiModels, FragmentManager fragmentManager) {
        this.context = context;
        this.edukasiModels = edukasiModels;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public EdukasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita_item, parent
                , false);
        return new EdukasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EdukasiViewHolder holder, int position) {

        if(edukasiModels != null && edukasiModels.size() > 0) {
            EdukasiModel edukasiModel = edukasiModels.get(position);

            // JUDUL
            String judulTeks;
            if(edukasiModel.getJudul_edukasi().length() > 50) {
                judulTeks = edukasiModel.getJudul_edukasi().substring(0, 50) + "...";
            } else {
                judulTeks = edukasiModel.getJudul_edukasi();
            }
            holder.txtJudul.setText(judulTeks);

            Glide.with(holder.itemView.getContext())
                    .load(edukasiModel.getImage_url())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(holder.imgEdukasi);

            // WAKTU
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                Date pastTime = sdf.parse(edukasiModel.getCreated_at());
                Date currentTime = new Date();

                long differenceInMillis = Math.abs(currentTime.getTime() - pastTime.getTime());

                long seconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMillis);
                long hours = TimeUnit.MILLISECONDS.toHours(differenceInMillis);
                long days = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

                String timeAgo;
                if (seconds < 60) {
                    timeAgo = seconds + " detik yang lalu";
                } else if (minutes < 60) {
                    timeAgo = minutes + " menit yang lalu";
                } else if (hours < 24) {
                    timeAgo = hours + " jam yang lalu";
                } else {
                    timeAgo = days + " hari yang lalu";
                }

                holder.txtTanggal.setText(timeAgo);

            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("Jam Error", "Gagal ubah format waktu");
            }

            boolean isLoveled = false;

            if(edukasiModel.getLike_user().equals("OT0000000001")) {
                isLoveled = true;
                edukasiModel.setLoved(true);
            }

            holder.btnLova.setImageResource(isLoveled ? R.drawable.ic_love_filled : R.drawable.ic_love_outline);
            holder.btnLova.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String user_like = "OT0000000001";

                    boolean newLoveStatus = !edukasiModel.isLoved();
                    edukasiModel.setLoved(newLoveStatus);

                    holder.btnLova.setImageResource(newLoveStatus ? R.drawable.ic_love_filled :
                            R.drawable.ic_love_outline);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/Molita/Public/index.php/")  // Ganti dengan URL API Anda
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiService apiService = retrofit.create(ApiService.class);

                    Call<ResponseListData> call =
                            apiService.updateEdukasiPartial(newLoveStatus ? user_like : "NULL",
                                    edukasiModel.getId_edukasi());

                    call.enqueue(new Callback<ResponseListData>() {
                        @Override
                        public void onResponse(Call<ResponseListData> call, Response<ResponseListData> response) {
                            if (response.isSuccessful()) {
                                Log.d("Update", "Data berhasil diperbarui");
                            } else {
                                Log.e("Update", "Gagal memperbarui data: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseListData> call, Throwable t) {
                            Log.e("Update", "Error: " + t.getMessage());
                        }
                    });
                }
            });

            // AKSI KLIK ITEM CARD EDUKASI
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String inputDate = edukasiModel.getCreated_at(); // Format dinamis

                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                    SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm z", new Locale("id", "ID"));

                    try {
                        Date date = inputFormat.parse(inputDate);

                        String formattedDate = outputFormat.format(date);
                        Bundle bundle = new Bundle();
                        bundle.putString("imgUrlEdukasi", edukasiModel.getImage_url());
                        bundle.putString("judulEdukasi", edukasiModel.getJudul_edukasi());
                        bundle.putString("tanggalWaktu", formattedDate);
                        bundle.putString("deskripsiEdukasi", edukasiModel.getDeskripsi_edukasi());

                        // Membuat fragment baru dan mengatur Bundle data
                        ShowEdukasiFragment showEdukasiFragment = new ShowEdukasiFragment();
                        showEdukasiFragment.setArguments(bundle);

                        // Memulai transaksi fragment untuk mengganti fragment
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_container, showEdukasiFragment); // Ganti R.id.fragment_container dengan ID container fragment Anda
                        transaction.addToBackStack(null); // Jika Anda ingin fragment sebelumnya dapat dikembalikan
                        transaction.commit();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return edukasiModels.size();
    }

    public void setEdukasiList(List<EdukasiModel> newList) {
        if (newList != null && !newList.isEmpty() && newList.size() > 0) {
            if(newList.get(0).getJudul_edukasi() != null) {
                Log.d("EdukasiAdapter", "New List Size: " + newList.size());
                Log.d("EdukasiAdapter222", "Old List Size: " + edukasiModels.size());

                // Clear existing list and add new data
                this.edukasiModels.clear();
                this.edukasiModels.addAll(newList);
                notifyDataSetChanged();
            } else {
                this.edukasiModels.clear();
                notifyDataSetChanged();
            }

        } else {
            Log.d("EdukasiAdapter", "New List is null or empty");
            this.edukasiModels.clear();
            notifyDataSetChanged();
        }
    }


    public static class EdukasiViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul;
        TextView txtTanggal;
        ImageView imgEdukasi;
        ImageView btnLova;

        public EdukasiViewHolder(View itemView) {
            super(itemView);

            this.txtJudul = itemView.findViewById(R.id.txtJudul);
            this.txtTanggal = itemView.findViewById(R.id.txtTanggal);
            this.imgEdukasi = itemView.findViewById(R.id.imgEdukasi);
            this.btnLova = itemView.findViewById(R.id.btnLove);
        }

    }
}
