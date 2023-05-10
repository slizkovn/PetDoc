package com.example.petdoc.ui.schedule;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.ui.adapters.AppointmentsAdapter;
import com.example.petdoc.ui.appointments.AppointmentAddFragment;
import com.example.petdoc.ui.appointments.AppointmentsFragment;
import com.example.petdoc.ui.view_models.AppointmentsViewModel;
import com.example.petdoc.ui.view_models.PetsViewModel;

import java.util.ArrayList;
import java.util.List;


public class ScheduleFragment extends Fragment {

    private List<Appointment> appointments = new ArrayList<>();
    private AppointmentsViewModel appointmentsViewModel;
    private PetsViewModel petsViewModel;
    public RecyclerView.Adapter adapter = new AppointmentsAdapter(this.appointments, 1, null);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        RecyclerView recycler = v.findViewById(R.id.scheduleRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);
        appointmentsViewModel = new AppointmentsViewModel(getActivity());
        petsViewModel = new PetsViewModel(getActivity());
        refresh();

        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Appointment> appointmentsList = appointmentsViewModel.getPaidAppointments();
        appointments.clear();
        appointments.addAll(appointmentsList);
        adapter.notifyDataSetChanged();
    }
}