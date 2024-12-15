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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.molita.molita.R;
import com.molita.molita.viewmodel.auth.AuthViewModel;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtMasuk, txtSudah;
    TextInputEditText txtEmail, txtUsername, txtPassword1, txtPassword2;
    Button btnRegistrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // GET XML
        txtEmail = findViewById(R.id.edtEmail);
        txtUsername = findViewById(R.id.edtUsername);
        txtPassword1 = findViewById(R.id.edtPassword);
        txtPassword2 = findViewById(R.id.edtKonfirmasi);
        btnRegistrasi = findViewById(R.id.btnDaftar);
        txtMasuk = findViewById(R.id.txtMasuk);
        txtSudah = findViewById(R.id.txtSudah);

        txtSudah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });

        AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getIsRegistrationSuccessful().observe(this, isSuccess -> {
            if (isSuccess) {
                // Registrasi berhasil, pindah ke halaman berikutnya
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish(); // Tutup halaman registrasi
            } else {
                // Registrasi gagal, tampilkan pesan error
                Log.d("PESAN", authViewModel.getStatusMessage().getValue());
                Toast.makeText(this, authViewModel.getStatusMessage().getValue(), Toast.LENGTH_SHORT).show();
            }
        });

        // Memanggil metode registrasi
        btnRegistrasi.setOnClickListener(v -> {
            String email = txtEmail.getText().toString();
            String username = txtUsername.getText().toString();
            String pass1 = txtPassword1.getText().toString();
            String pass2 = txtPassword2.getText().toString();


            if (!email.isEmpty() && !username.isEmpty() && !pass1.isEmpty() && !pass2.isEmpty() ) {
                if(txtPassword1.getText().toString().equals(txtPassword2.getText().toString())) {
                    authViewModel.register(email, username, pass1);
                } else {
                    Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Email dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void navigateToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}