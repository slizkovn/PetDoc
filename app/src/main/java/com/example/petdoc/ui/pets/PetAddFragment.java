package com.example.petdoc.ui.pets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.ui.view_models.PetsViewModel;


public class PetAddFragment extends Fragment {
    private PetsViewModel petsViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_add, container, false);

        petsViewModel = new PetsViewModel(getActivity());

        Spinner spinner = (Spinner) v.findViewById(R.id.petType);
        String[] PetTypes = {"Dog", "Cat", "Turtle"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, PetTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        Button createButton = (Button) v.findViewById(R.id.createPet_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextName
                EditText name = (EditText) getView().findViewById(R.id.editTextPetName);
                EditText age = (EditText) getView().findViewById(R.id.editTextPetAge);
                EditText history = (EditText) getView().findViewById(R.id.editTextPetHistory);
                int n = 0;
                if (!String.valueOf(age.getText()).equals("")){
                    n = Integer.parseInt(String.valueOf(age.getText()));
                }
                Spinner type = (Spinner) getView().findViewById(R.id.petType);

                if (String.valueOf(name.getText()).equals("")){
                    Toast.makeText(getActivity(), "Give your pet a name", Toast.LENGTH_LONG).show();
                } else {
                    addPet(String.valueOf(name.getText()), n, type.getSelectedItem().toString(), history.toString());
                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.changeFragment(new PetsFragment());
                }
            }
        });

        Button backButton = (Button) v.findViewById(R.id.backToPets_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetsFragment());
            }
        });
        return v;
    }


    private void addPet(String name, int age, String type, String history) {
        petsViewModel.insertPet(new Pet(name, age, type, history));
    }
}