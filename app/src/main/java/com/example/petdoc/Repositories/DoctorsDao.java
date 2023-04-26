package com.example.petdoc.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoctorsDao  {

    @Insert
    void insertDoctor(Doctor doctor);

    @Delete
    void delete(Doctor doctor);


    @Query("SELECT * FROM doctors")
    List<Doctor> getDoctors();

    @Query("SELECT DISTINCT docType FROM doctors")
    List<String> getDoctorsTypes();

}
