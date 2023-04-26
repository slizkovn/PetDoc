package com.example.petdoc.Adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.Repositories.Appointment;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Appointment> appointments;
    public AppointmentsAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.appointment, parent, false)
        ){};
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView petName = holder.itemView.findViewById(R.id.petNameText);
        TextView doctor = holder.itemView.findViewById(R.id.doctorText);
        TextView date = holder.itemView.findViewById(R.id.dateText);
        petName.setText(appointments.get(position).getPet());
        doctor.setText("Doctor: " + appointments.get(position).getDoctor());
        date.setText(appointments.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}