package com.example.petdoc.ui.pets;

import static com.example.petdoc.MainActivity.USERID;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.petdoc.ui.adapters.PetsAdapter;
import com.example.petdoc.MainActivity;
import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.R;
import com.example.petdoc.ui.adapters.PetsRecycleViewInterface;
import com.example.petdoc.ui.view_models.PetsViewModel;

import java.util.ArrayList;
import java.util.List;


public class PetsFragment extends Fragment implements PetsRecycleViewInterface {
    private List<Pet> pets = new ArrayList<>();
    private PetsViewModel petsViewModel;
    public RecyclerView.Adapter adapter = new PetsAdapter(this.pets, this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pets, container, false);
        RecyclerView recycler = v.findViewById(R.id.PetsRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        petsViewModel = new PetsViewModel(getActivity());
        refresh();

        ImageButton appendButton = (ImageButton) v.findViewById(R.id.append_button);
        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetAddFragment());
            }
        });

        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Pet> petsList = petsViewModel.getPets();
        pets.clear();
        pets.addAll(petsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Pet pet) {
        Log.i("pet", ""+pet.getName());
        petsViewModel.deletePet(this.getActivity(), pet);
        refresh();
    }
}