package com.example.petdoc.Fragments;

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

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.Repositories.Pet;
import com.example.petdoc.Repositories.PetDatabase;


public class PetAddFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_add, container, false);
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
                int n = 0;
                if (!String.valueOf(age.getText()).equals("")){
                    n = Integer.parseInt(String.valueOf(age.getText()));
                }
                Spinner type = (Spinner) getView().findViewById(R.id.petType);
                Log.i("PetAdd", name.getText()+" "+age.getText()+" "+type.getSelectedItem().toString()); //ИСПРАВЬ ТИП ЖИВОТНОГО
                addPet(String.valueOf(name.getText()), n, type.getSelectedItem().toString());
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetsFragment());
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


    private void addPet(String name, int age, String type) {
        PetDatabase db = PetDatabase.getAppDatabase(getActivity());
        if (name == ""){name = "PET";}
        db.PetDao().insertPet(new Pet(name, age, type));
    }
}