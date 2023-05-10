package com.example.petdoc.ui.view_models;

import android.app.Activity;

import com.example.petdoc.data.entities.Doctor;
import com.example.petdoc.data.repositories.DoctorsRepository;

import java.util.List;

public class DoctorsViewModel {
    private static DoctorsRepository doctorsRepository;

    public DoctorsViewModel(Activity activity) {
        doctorsRepository = new DoctorsRepository();
    }

    public List<Doctor> getDoctors() { return doctorsRepository.getFreeDoctors();
    }

    public List<String> getDoctorsTypes() { return doctorsRepository.getFreeDoctorsTypes();
    }

    public void deleteDoctor(Doctor i) {doctorsRepository.deleteDoctor(i);}

    public void insertDoctor(Doctor i){doctorsRepository.insertDoctor(i);}
}
