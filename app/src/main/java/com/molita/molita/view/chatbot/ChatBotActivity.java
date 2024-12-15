package com.molita.molita.view.chatbot;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.view.adapter.ChatAdapter;
import com.molita.molita.viewmodel.chat.ChatViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatBotActivity extends AppCompatActivity {

    private ChatViewModel chatViewModel;
    private ChatAdapter chatAdapter;
    private EditText editTextMessage;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.chatbot_activity);

        back = findViewById(R.id.back);
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        RecyclerView recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        ImageButton buttonSend = findViewById(R.id.buttonSend);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String currentTime = hour + ":" + minute;

        chatAdapter = new ChatAdapter(new ArrayList<>());
        recyclerViewChat.setAdapter(chatAdapter);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));

        chatViewModel.getMessages().observe(this, messages -> {
            chatAdapter.updateMessages(messages);
            int targetPosition = messages.size() - 1;
            if (targetPosition >= 0) {
                recyclerViewChat.smoothScrollToPosition(targetPosition);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Action pada tombol kirim pesan
        buttonSend.setOnClickListener(v -> sendMessage(currentTime));

        String curretMessage = "Hai! Selamat datang di layanan customer service Posyandu. Pilih " +
                "nomor " +
                "di " +
                "bawah ini untuk melanjutkan:\n" +
                "1. Jadwal Imunisasi\n" +
                "2. Riwayat Kunjungan\n" +
                "3. Fitur Aplikasi\n" +
                "4. Masalah Teknis\n" +
                "5. Data Anak\n" +
                "6. Bantuan Langsung";
        simulateReceiveMessage(curretMessage, currentTime);
    }

    private void sendMessage(String currentTime) {
        String messageText = editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            chatViewModel.sendMessage(messageText, currentTime);
            editTextMessage.setText("");
        }

        if(messageText.equals("1")) {
            String pesan = "Untuk informasi jadwal imunisasi anak, Anda bisa melihat langsung di " +
                    "halaman 'Jadwal Imunisasi' di aplikasi. Apakah ada yang ingin ditanyakan lebih lanjut?";
            chatViewModel.receiveMessage(pesan, currentTime);
        } else if(messageText.equals("2")) {
            String pesan = "Anda dapat melihat riwayat kunjungan anak di halaman 'Riwayat'. Jika " +
                    "butuh panduan, kami siap membantu lebih lanjut.";
            chatViewModel.receiveMessage(pesan, currentTime);
        } else if(messageText.equals("3")) {
            String pesan = "Jika ada masalah teknis, cobalah menutup dan membuka kembali aplikasi" +
                    ". Apabila masalah masih ada, silakan jelaskan lebih lanjut untuk kami bantu.";
            chatViewModel.receiveMessage(pesan, currentTime);
        } else if(messageText.equals("4")) {
            String pesan = "Jika ada masalah teknis, cobalah menutup dan membuka kembali aplikasi" +
                    ". Apabila masalah masih ada, silakan jelaskan lebih lanjut untuk kami bantu.";
            chatViewModel.receiveMessage(pesan, currentTime);
        } else if(messageText.equals("5")) {
            String pesan = "Untuk melihat data anak, Anda bisa mengakses menu 'Data Anak' di " +
                    "aplikasi. Jika ada data yang perlu diperbarui, hubungi admin posyandu.";
            chatViewModel.receiveMessage(pesan, currentTime);
        } else if(messageText.equals("6")) {
            String pesan = "Untuk bantuan lebih lanjut, Anda bisa menghubungi admin posyandu atau" +
                    " melalui menu 'Bantuan' di aplikasi. Ada lagi yang bisa kami bantu?";
            chatViewModel.receiveMessage(pesan, currentTime);
        } else {
            String pesan = "Pesan yang anda masukan tidak dimengerti";
            chatViewModel.receiveMessage(pesan, currentTime);
        }
    }

    private void simulateReceiveMessage(String pesan, String currentTime) {
        new Handler().postDelayed(() -> {
            chatViewModel.receiveMessage(pesan, currentTime);
        }, 1000);
    }
}


