package com.example.petdoc.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessagesDao  {

    @Insert
    void insertMessage(Message msg);

    @Delete
    void delete(Message msg);


    @Query("SELECT * FROM messages")
    List<Message> getMessages();

}
