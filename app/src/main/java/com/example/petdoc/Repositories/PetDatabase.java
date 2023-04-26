package com.example.petdoc.Repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pet.class}, version = 1)
public abstract class PetDatabase extends RoomDatabase {
    private static PetDatabase INSTANCE;
    public abstract PetDao PetDao();
    public static PetDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), PetDatabase.class, "user_pets")
                            .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}