package com.example.petdoc.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.petdoc.Adapters.MessagesAdapter;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.Repositories.Appointment;
import com.example.petdoc.Repositories.AppointmentsDatabase;
import com.example.petdoc.Repositories.Message;
import com.example.petdoc.Repositories.MessagesDatabase;
import com.example.petdoc.Repositories.PetDatabase;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatFragment extends Fragment{
    private List<Message> messages = new ArrayList<>();
    //public List<Pet> petsListFromDb = new ArrayList<>();
    //public MessagesDatabase db;
    public RecyclerView.Adapter adapter = new MessagesAdapter(this.messages);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        EditText text = (EditText) v.findViewById(R.id.messageSendText);

        RecyclerView recycler = v.findViewById(R.id.ChatRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        ImageButton appendButton = (ImageButton) v.findViewById(R.id.messageSend_button);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessagesDatabase db = MessagesDatabase.getMessagesDatabase(getActivity());
                Date date = Calendar.getInstance().getTime();
                db.MessagesDao().insertMessage(new Message(1, text.getText().toString(), String.valueOf(date)));
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new ChatFragment());
            }
        });

        ImageButton callButton = (ImageButton) v.findViewById(R.id.videocall_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                //mainActivity.showWinbar();
                mainActivity.changeFragment(new VideoCallFragment());
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                //mainActivity.hideWinbar();
            }

        });

        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        MessagesDatabase db = MessagesDatabase.getMessagesDatabase(getActivity());
        messages.clear();
        for (int i = 0;i < db.MessagesDao().getMessages().size(); ++i){
            Message p = db.MessagesDao().getMessages().get(i);
            messages.add(new Message(p.getUserId(), p.getText(), p.getDate()));
        }
        adapter.notifyDataSetChanged();
    }
}