package com.molita.molita.view.slider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.molita.molita.R;
import com.molita.molita.view.adapter.SliderAdapter;
import com.molita.molita.view.auth.LoginActivity;

public class SliderActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout dotsLayout;
    private Button btnNext;
    private SliderAdapter adapter;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slider);

        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        btnNext = findViewById(R.id.btnNext);

        adapter = new SliderAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.post(() -> addDotsIndicator(0));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                addDotsIndicator(position);
                currentPage = position;

                if(currentPage == adapter.getItemCount() - 1) {
                    btnNext.setText("Mulai");
                } else {
                    btnNext.setText("Selanjutnya");
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage < adapter.getItemCount() - 1) {
                    viewPager.setCurrentItem(currentPage + 1);
                    if(currentPage == adapter.getItemCount() - 1) {
                        btnNext.setText("Mulai");
                    } else {
                        btnNext.setText("Selanjutnya");
                    }
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void addDotsIndicator(int position) {
        dotsLayout.removeAllViews();  // Menghapus semua titik sebelumnya
        TextView[] dots = new TextView[adapter.getItemCount()];  // Buat array untuk titik

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));  // Set titik menggunakan kode HTML
            dots[i].setTextSize(50);
            dots[i].setTextColor(getResources().getColor(R.color.secondaryOne)); // Warna titik tidak aktif
            dotsLayout.addView(dots[i]);  // Tambahkan titik ke layout
        }

        // Warna untuk titik aktif
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.primaryOne));
        }
    }

}
