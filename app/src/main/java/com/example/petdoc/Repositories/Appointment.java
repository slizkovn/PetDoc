package com.example.petdoc.Repositories;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "pet")
    private String pet;

    @ColumnInfo(name = "doctor")
    private String doctor;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "price")
    private int price;


    public Appointment(String pet, String doctor, String date, int price) {
        this.pet = pet;
        this.doctor = doctor;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPet() {
        return pet;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }
}
