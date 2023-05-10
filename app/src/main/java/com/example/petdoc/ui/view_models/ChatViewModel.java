package com.example.petdoc.ui.view_models;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.example.petdoc.data.entities.Message;
import com.example.petdoc.data.repositories.AppointmentsRepository;
import com.example.petdoc.data.repositories.ChatRepository;

import java.util.List;

public class ChatViewModel {
    private static ChatRepository chatRepository;

    public ChatViewModel(Activity activity) {
        chatRepository = new ChatRepository();
    }

    public static List<Message> getMessages() { return chatRepository.getMessages();}

    public void insertMessage(Message message) { chatRepository.insertMessage(message);
    }
}
