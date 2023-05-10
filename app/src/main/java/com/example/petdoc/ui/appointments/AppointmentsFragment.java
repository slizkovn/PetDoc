package com.example.petdoc.ui.appointments;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.data.repositories.AppointmentsRepository;
import com.example.petdoc.ui.adapters.AppointmentsAdapter;
import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.data.data_room.database.AppointmentsDatabase;
import com.example.petdoc.ui.adapters.AppointmentsRecycleViewInterface;
import com.example.petdoc.ui.view_models.AppointmentsViewModel;
import com.example.petdoc.ui.view_models.PetsViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsFragment extends Fragment implements AppointmentsRecycleViewInterface {
    private List<Appointment> appointments = new ArrayList<>();
    private AppointmentsViewModel appointmentsViewModel;
    private PetsViewModel petsViewModel;
    public RecyclerView.Adapter adapter = new AppointmentsAdapter(this.appointments, 0, this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointments, container, false);
        RecyclerView recycler = v.findViewById(R.id.appointmentsRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);
        appointmentsViewModel = new AppointmentsViewModel(getActivity());
        petsViewModel = new PetsViewModel(getActivity());
        refresh();


        ImageButton appendButton = (ImageButton) v.findViewById(R.id.appendAppointment_button);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (petsViewModel.getPets().size() == 0){
                    Toast.makeText(getActivity(), "First you need to add a pet", Toast.LENGTH_LONG).show();
                } else {
                    ConnectivityManager connectivityManager =  (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()){
                        MainActivity mainActivity = (MainActivity)getActivity();
                        mainActivity.changeFragment(new AppointmentAddFragment());
                    } else {
                        Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        ImageButton payButton = (ImageButton) v.findViewById(R.id.payAppointments_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Paid. You can see your doctor's appointments in the next tab", Toast.LENGTH_LONG).show();
                appointmentsViewModel.payAll();
                refresh();
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new AppointmentsFragment());
            }
        });

        TextView cost = (TextView) v.findViewById(R.id.totalCost);
        int appointmentssCost = 0;
        for (Appointment i : appointmentsViewModel.getAppointments()){appointmentssCost += i.getPrice();}
        cost.setText("Total cost: "+appointmentssCost+" Rub");
        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Appointment> appointmentsList = appointmentsViewModel.getAppointments();
        appointments.clear();
        appointments.addAll(appointmentsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Appointment appointment) {
        appointmentsViewModel.deleteAppointment(appointment);
        refresh();
    }
}