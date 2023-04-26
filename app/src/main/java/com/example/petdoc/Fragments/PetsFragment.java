package com.example.petdoc.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.petdoc.Adapters.PetsAdapter;
import com.example.petdoc.MainActivity;
import com.example.petdoc.Repositories.Appointment;
import com.example.petdoc.Repositories.AppointmentsDatabase;
import com.example.petdoc.Repositories.Pet;
import com.example.petdoc.Repositories.PetDatabase;
import com.example.petdoc.R;

import java.util.ArrayList;
import java.util.List;


public class PetsFragment extends Fragment{
    private List<Pet> pets = new ArrayList<>();
    //public List<Pet> petsListFromDb = new ArrayList<>();
    public PetDatabase db;
    public RecyclerView.Adapter adapter = new PetsAdapter(this.pets);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //PetDatabase db = PetDatabase.getAppDatabase(getActivity());
        //List<Pet> petsListFromDb = db.PetDao().getPets();
        //Log.i("START", petsListFromDb.size()+"");
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pets, container, false);
        RecyclerView recycler = v.findViewById(R.id.PetsRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        Button appendButton = (Button) v.findViewById(R.id.append_button);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetAddFragment());
            }
        });

        Button deleteButton = (Button) v.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllPets();
                refresh();
            }
        });
        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        PetDatabase db = PetDatabase.getAppDatabase(getActivity());
        pets.clear();
        for (int i = 0;i < db.PetDao().getPets().size(); ++i){
            Pet p = db.PetDao().getPets().get(i);
            //Log.i("refresh", ""+i);
            pets.add(new Pet(p.getName(), p.getAge(), p.getType()));
        }
        adapter.notifyDataSetChanged();
    }

    public void deleteAllPets(){
        PetDatabase db = PetDatabase.getAppDatabase(getActivity());
        for (Pet i : db.PetDao().getPets()){
            db.PetDao().delete(i);
        }
        //Log.i("deleter", "lol");
        AppointmentsDatabase db2 = AppointmentsDatabase.getAppointmentsDatabase(getActivity());
        for (Appointment i : db2.AppointmentDao().getAppointments()){
            db2.AppointmentDao().delete(i);
        }
        //Log.i("deleter_appoints", "lol");
    }
}