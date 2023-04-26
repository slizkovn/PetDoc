package com.example.petdoc.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PetDao {


    @Insert
    void insertPet(Pet... pet);


    @Delete
    void delete(Pet pet);


    @Query("SELECT * FROM user_pets")
    List<Pet> getPets();

}
