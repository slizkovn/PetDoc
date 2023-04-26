package com.example.petdoc.Repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Appointment.class}, version = 1)
public abstract class AppointmentsDatabase extends RoomDatabase {
    private static AppointmentsDatabase INSTANCE;
    public abstract AppointmentsDao AppointmentDao();
    public static AppointmentsDatabase getAppointmentsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppointmentsDatabase.class, "user_appointments")
                            .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}