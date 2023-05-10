package com.example.petdoc.ui.chat;

import static com.example.petdoc.MainActivity.USERID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.petdoc.ui.adapters.MessagesAdapter;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.data.entities.Message;
import com.example.petdoc.ui.view_models.ChatViewModel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatFragment extends Fragment{
    private List<Message> messages = new ArrayList<>();

    private ChatViewModel chatViewModel;
    public RecyclerView.Adapter adapter = new MessagesAdapter(this.messages);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        EditText text = (EditText) v.findViewById(R.id.messageSendText);

        chatViewModel = new ChatViewModel(getActivity());
        refresh();
        RecyclerView recycler = v.findViewById(R.id.ChatRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        showKeyboard(text);
        hideKeyboard(text);
        ((MainActivity)getActivity()).hideWinbar();
        ((MainActivity)getActivity()).showWinbar();

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (!connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting())
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG).show();
        } catch (Exception e){}
        recycler.scrollToPosition(messages.size() - 1);
        refresh();

        ScrollView sv = (ScrollView) v.findViewById(R.id.chatScroller);
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });
        ImageButton sendButton = (ImageButton) v.findViewById(R.id.messageSend_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = Calendar.getInstance().getTime();
                if (text.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Send a non-empty message", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()) {
                            String dateTime = date.toString();
                            dateTime = dateTime.substring(4, dateTime.length() - 12);
                            chatViewModel.insertMessage(new Message(USERID, 0, text.getText().toString(), dateTime));
                            hideKeyboard(text);
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.changeFragment(new ChatFragment());
                        } else {
                            Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG).show();
                        }
                    }   catch (Exception e) {}
            }}
        });

        ImageButton callButton = (ImageButton) v.findViewById(R.id.videocall_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.showWinbar();
                mainActivity.changeFragment(new VideoCallFragment());
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard(text);
            }

        });
        text.requestFocus();
        return v;
    }


    public void showKeyboard(EditText text){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.hideWinbar();
    }

    public void hideKeyboard(EditText text){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(), 0);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.showWinbar();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Message> messagesList = chatViewModel.getMessages();
        messages.clear();
        messages.addAll(messagesList);
        adapter.notifyDataSetChanged();
    }
}