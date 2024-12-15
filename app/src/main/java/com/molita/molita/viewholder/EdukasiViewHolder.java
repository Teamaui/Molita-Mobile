package com.molita.molita.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;

public class EdukasiViewHolder extends RecyclerView.ViewHolder {

    public TextView tvJudul;
    public TextView tvTeks;

    public EdukasiViewHolder(View view) {
        super(view);
        tvJudul = view.findViewById(R.id.tvJudul);
    }
}
