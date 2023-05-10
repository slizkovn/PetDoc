package com.example.petdoc.ui.appointments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.data.entities.Doctor;
import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.ui.view_models.AppointmentsViewModel;
import com.example.petdoc.ui.view_models.DoctorsViewModel;
import com.example.petdoc.ui.view_models.PetsViewModel;

import java.util.ArrayList;
import java.util.List;


public class AppointmentAddFragment extends Fragment {

    private PetsViewModel petsViewModel = new PetsViewModel(getActivity());
    String[] PETTYPE = {petsViewModel.getPets().get(0).getType()};
    String[] DOCDATE = {""};
    String[] DOCTYPE = {""};
    String[] DOCNAME = {""};
    String[] PETNAME = {petsViewModel.getPets().get(0).getName()};
    int PRICE = 0;
    DoctorsViewModel doctorsViewModel;
    AppointmentsViewModel appointmentsViewModel;
    List<Doctor> Docs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointment_add, container, false);

        appointmentsViewModel = new AppointmentsViewModel(getActivity());

        doctorsViewModel = new DoctorsViewModel(getActivity());
        Docs = doctorsViewModel.getDoctors();
        Spinner PetNameSpinner = (Spinner) v.findViewById(R.id.petForAppointmentName);

        List<Pet> petsListFromDb = petsViewModel.getPets();
        List<String> PetNames = new ArrayList<String>(){};
        for (int i = 0; i < petsListFromDb.size(); ++i){
            PetNames.add(petsListFromDb.get(i).getName());
        }
        ArrayAdapter<String> PetNameAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, PetNames);
        PetNameAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        PetNameSpinner.setAdapter(PetNameAdapter);


        Spinner DocTypeSpinner = (Spinner) v.findViewById(R.id.doctorTypeForAppointment);
        List<String> DocsTypes = doctorsViewModel.getDoctorsTypes();
        ArrayAdapter<String> DocTypeAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, DocsTypes);
        DocTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        DocTypeSpinner.setAdapter(DocTypeAdapter);


        Spinner DocSpinner = (Spinner) v.findViewById(R.id.doctorForAppointment);
        List<String> SuitableDoctors = new ArrayList<String>(){};
        ArrayAdapter<String> DocAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, SuitableDoctors);
        DocAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        DocSpinner.setAdapter(DocAdapter);

        Spinner DateSpinner = (Spinner) v.findViewById(R.id.dateForAppointment);
        List<String> SuitableDates = new ArrayList<String>(){};
        ArrayAdapter<String> DateAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, SuitableDates);
        DateAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        DateSpinner.setAdapter(DateAdapter);

        PetNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] DOCDATE = {""};
                String[] DOCTYPE = {""};
                String[] DOCNAME = {""};
                DocsTypes.clear();
                for (Pet i: petsListFromDb){
                    if (i.getName().equals(PetNames.get(selectedItemPosition))){
                        PETTYPE[0] = String.valueOf(i.getType());
                        PETNAME[0] = i.getName();
                    }
                }
                DocsTypes.clear();
                for (Doctor i : Docs){
                    if (i.getAnimalType().equals(PETTYPE[0]) && !DocsTypes.contains(i.getDocType())){DocsTypes.add(i.getDocType());}
                }
                SuitableDoctors.clear();
                SuitableDates.clear();
                DocAdapter.notifyDataSetChanged();
                DateAdapter.notifyDataSetChanged();
                DocTypeAdapter.notifyDataSetChanged();
                ((TextView) v.findViewById(R.id.appointment_price)).setText("- Rub");
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DocTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                SuitableDoctors.clear();
                SuitableDates.clear();
                for (Doctor i: Docs){
                    if (DocsTypes.get(selectedItemPosition).equals(i.getDocType()) && i.getAnimalType().equals(PETTYPE[0]) && !SuitableDoctors.contains(i.getName())){
                        SuitableDoctors.add(i.getName());
                    }
                }
                DOCTYPE[0] = DocsTypes.get(selectedItemPosition);
                String[] DOCDATE = {""};
                String[] DOCNAME = {""};
                DocAdapter.notifyDataSetChanged();
                DateAdapter.notifyDataSetChanged();
                DocTypeAdapter.notifyDataSetChanged();
                ((TextView) v.findViewById(R.id.appointment_price)).setText("- Rub");
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DocSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                SuitableDates.clear();
                String[] DOCDATE = {""};
                DOCNAME[0] = SuitableDoctors.get(selectedItemPosition);
                for(Doctor i : Docs){
                    if (i.getAnimalType().equals(PETTYPE[0]) && i.getDocType().equals(DOCTYPE[0]) && i.getName().equals(SuitableDoctors.get(selectedItemPosition))){
                        SuitableDates.add(i.getDate());
                    }
                }
                ((TextView) v.findViewById(R.id.appointment_price)).setText("- Rub");
                DateAdapter.notifyDataSetChanged();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                DOCDATE[0] = SuitableDates.get(selectedItemPosition);
                for(Doctor i : Docs){
                    if (i.getAnimalType().equals(PETTYPE[0]) && i.getDocType().equals(DOCTYPE[0]) && i.getDate().equals(SuitableDates.get(selectedItemPosition)) && i.getName().equals(DOCNAME[0])){
                        TextView price = (TextView) v.findViewById(R.id.appointment_price);
                        price.setText(DOCNAME[0] + " " + i.getPrice() + " Rub");
                        PRICE = i.getPrice();
                    }
                }
                if (PRICE == 0){
                    Toast.makeText(getActivity(), "There are no doctors available for this pet", Toast.LENGTH_LONG).show();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        Button createButton = (Button) v.findViewById(R.id.createAppointment_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PRICE != 0){
                    appointmentsViewModel.insertAppointment(new Appointment(PETNAME[0], DOCNAME[0], DOCTYPE[0], DOCDATE[0], PRICE, 0));
                    for (Doctor i : doctorsViewModel.getDoctors()){
                        if (i.getDate().equals(DOCDATE[0]) && i.getPrice() == PRICE && i.getName().equals(DOCNAME[0])){
                            doctorsViewModel.deleteDoctor(i);
                        }
                    }
                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.changeFragment(new AppointmentsFragment());
                } else {
                    Toast.makeText(getActivity(), "There are no doctors available for this pet", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button backButton = (Button) v.findViewById(R.id.backToAppointments_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new AppointmentsFragment());
            }
        });
        return v;
    }
}