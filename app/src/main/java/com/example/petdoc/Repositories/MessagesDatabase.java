package com.example.petdoc.Repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class}, version = 1)
public abstract class MessagesDatabase extends RoomDatabase {
    private static MessagesDatabase INSTANCE;
    public abstract MessagesDao MessagesDao();
    public static MessagesDatabase getMessagesDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MessagesDatabase.class, "messages")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}