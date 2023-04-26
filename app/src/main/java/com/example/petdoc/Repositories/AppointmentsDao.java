package com.example.petdoc.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentsDao {


    @Insert
    void insertAppointment(Appointment... appointment);


    @Delete
    void delete(Appointment appointment);


    @Query("SELECT * FROM user_appointments")
    List<Appointment> getAppointments();

}
