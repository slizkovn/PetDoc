package com.example.petdoc.Repositories;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity(tableName = "doctors")
public class Doctor {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "docType")
    public String docType;

    @ColumnInfo(name = "animalType")
    public String animalType;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "price")
    public int price;

    public Doctor(String name, String docType, String animalType, String date, int price){
        this.name = name;
        this.price = price;
        this.animalType = animalType;
        this.date = date;
        this.docType = docType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getDocType() {
        return docType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public void setName(String name) {
        this.name = name;
    }
}

