package com.molita.molita.viewmodel.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.molita.molita.model.data.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private MutableLiveData<List<Message>> messages = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public void sendMessage(String messageText, String currentTime) {

        List<Message> currentMessages = messages.getValue();
        if (currentMessages != null) {
            currentMessages.add(new Message(messageText, true, currentTime)); // Tambahkan pesan baru
            messages.setValue(new ArrayList<>(currentMessages)); // Update LiveData dengan data terbaru
        }
    }

    // Terima pesan baru (misalnya dari server atau sumber lain)
    public void receiveMessage(String messageText, String currentTime) {

        List<Message> currentMessages = messages.getValue();
        if (currentMessages != null) {
            currentMessages.add(new Message(messageText, false, currentTime)); // Pesan diterima dari server
            messages.setValue(new ArrayList<>(currentMessages)); // Update LiveData
        }
    }
}

