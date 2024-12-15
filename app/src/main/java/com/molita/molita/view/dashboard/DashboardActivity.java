package com.molita.molita.view.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.molita.molita.R;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.chatbot.ChatBotActivity;
import com.molita.molita.view.fragment.DashboardFragment;
import com.molita.molita.view.fragment.EdukasiFragment;
import com.molita.molita.view.fragment.GrafikFragment;
import com.molita.molita.view.fragment.HomeFragment;
import com.molita.molita.view.fragment.PenjadwalanFragment;
import com.molita.molita.view.fragment.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AuthRepository authRepository;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        floatingActionButton = findViewById(R.id.fab);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DashboardFragment()).commit();
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        authRepository = new AuthRepository(getApplicationContext());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idItem = item.getItemId();

                if(idItem == R.id.nav_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (idItem == R.id.nav_grafik) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GrafikFragment()).commit();
                    floatingActionButton.setVisibility(View.GONE);
                } else if (idItem == R.id.nav_edukasi) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EdukasiFragment()).commit();
                    floatingActionButton.setVisibility(View.GONE);
                } else if (idItem == R.id.nav_jadwal) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PenjadwalanFragment()).commit();
                    floatingActionButton.setVisibility(View.GONE);
                } else if (idItem == R.id.nav_profil) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                    floatingActionButton.setVisibility(View.GONE);
                }

                return true;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatBotActivity.class);
                startActivity(intent);
            }
        });

    }
}