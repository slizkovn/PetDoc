package com.example.petdoc.Repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Doctor.class}, version = 1)
public abstract class DoctorsDatabase extends RoomDatabase {
    private static DoctorsDatabase INSTANCE;
    public abstract DoctorsDao DoctorsDao();
    public static DoctorsDatabase getDoctorsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DoctorsDatabase.class, "doctors")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}