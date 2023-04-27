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
import android.widget.ImageButton;

import com.example.petdoc.Adapters.AppointmentsAdapter;
import com.example.petdoc.Adapters.PetsAdapter;
import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.Repositories.Appointment;
import com.example.petdoc.Repositories.AppointmentsDatabase;
import com.example.petdoc.Repositories.Pet;
import com.example.petdoc.Repositories.PetDatabase;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsFragment extends Fragment {
    private List<Appointment> appointments = new ArrayList<>();

    public RecyclerView.Adapter adapter = new AppointmentsAdapter(this.appointments);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointments, container, false);
        RecyclerView recycler = v.findViewById(R.id.appointmentsRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        ImageButton appendButton = (ImageButton) v.findViewById(R.id.appendAppointment_button);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new AppointmentAddFragment());
            }
        });

        ImageButton deleteButton = (ImageButton) v.findViewById(R.id.deleteAllAppointments_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllAppointments();
                refresh();
            }
        });
        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        AppointmentsDatabase db = AppointmentsDatabase.getAppointmentsDatabase(getActivity());
        appointments.clear();
        for (int i = 0; i < db.AppointmentDao().getAppointments().size(); ++i){
            Appointment a = db.AppointmentDao().getAppointments().get(i);
            appointments.add(new Appointment(a.getPet(), a.getDoctor(), a.getDate(), a.getPrice()));
        }
        adapter.notifyDataSetChanged();
    }

    public void deleteAllAppointments(){
        AppointmentsDatabase db = AppointmentsDatabase.getAppointmentsDatabase(getActivity());
        for (Appointment i : db.AppointmentDao().getAppointments()){
            db.AppointmentDao().delete(i);
        }
    }
}