package com.molita.molita.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.molita.molita.R;
import com.molita.molita.model.data.OrangTuaModel;
import com.molita.molita.model.repository.AuthRepository;
import com.molita.molita.view.dashboard.DashboardActivity;
import com.molita.molita.viewmodel.auth.AuthViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView txtBelum;
    private TextView txtDaftar;
    private TextInputEditText edtEmail, editTextPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        txtBelum = findViewById(R.id.txtBelum);
        txtDaftar = findViewById(R.id.txtDaftar);
        edtEmail = findViewById(R.id.edtEmail);
        editTextPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        txtBelum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegister();
            }
        });

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegister();
            }
        });

        AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getIsRegistrationSuccessful().observe(this, isSuccess -> {
            if (isSuccess) {

                authViewModel.getOrangTuaModel().observe(this, new Observer<List<OrangTuaModel>>() {
                    @Override
                    public void onChanged(List<OrangTuaModel> orangTuaModels) {
                        if(orangTuaModels != null && !orangTuaModels.isEmpty()) {
                            AuthRepository authRepository =
                                    new AuthRepository(getApplicationContext());
                            authRepository.saveUserId(orangTuaModels.get(0).getId_orang_tua());
                            authRepository.saveNameIbu(orangTuaModels.get(0).getNama_ibu());
                        }
                    }
                });

                // Registrasi berhasil, pindah ke halaman berikutnya
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Tutup halaman registrasi
            } else {
                // Registrasi gagal, tampilkan pesan error
                Log.d("PESAN", authViewModel.getStatusMessage().getValue());
                Toast.makeText(this, authViewModel.getStatusMessage().getValue(), Toast.LENGTH_SHORT).show();
            }
        });



        // Memanggil metode registrasi
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String pass1 = editTextPassword.getText().toString();

            if (!email.isEmpty() && !pass1.isEmpty() ) {
                    authViewModel.login(email, pass1);
            } else {
                Toast.makeText(this, "Email dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void navigateToDashboard() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}