package com.example.petdoc.Adapters;

import static android.graphics.Color.parseColor;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.Repositories.Message;
import com.example.petdoc.Repositories.Pet;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages = new ArrayList<>();
    public MessagesAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message, parent, false)
        ){};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView text = holder.itemView.findViewById(R.id.messageText);
        TextView date = holder.itemView.findViewById(R.id.messageDate);
        text.setText(messages.get(position).getText());
        date.setText(messages.get(position).getDate());
        LinearLayout ll = holder.itemView.findViewById(R.id.msg);
        if (messages.get(position).getUserId() == 0){
            ll.setBackgroundColor(Color.parseColor("#ffffa9a9"));
            ll.setGravity(Gravity.LEFT);
            text.setGravity(Gravity.LEFT);
            date.setGravity(Gravity.LEFT);
        }
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }
}