package com.example.petdoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.petdoc.Fragments.EntryFragment;
import com.example.petdoc.Fragments.WinbarFragment;
import com.example.petdoc.Repositories.Doctor;
import com.example.petdoc.Repositories.DoctorsDatabase;
import com.example.petdoc.Repositories.Message;
import com.example.petdoc.Repositories.MessagesDatabase;
import com.example.petdoc.Repositories.Pet;
import com.example.petdoc.Repositories.PetDatabase;
import com.example.petdoc.databinding.ActivityMainBinding;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        MessagesDatabase db = MessagesDatabase.getMessagesDatabase(this);
        for (Message i: db.MessagesDao().getMessages()){
            Log.i("1",i.getText()+" "+i.getDate()+" "+i.getUserId());
            db.MessagesDao().delete(i);
        }
        db.MessagesDao().insertMessage(new Message(1,"first", "19:30 23.04.2023"));
        db.MessagesDao().insertMessage(new Message(0,"second", "19:31 23.04.2023"));
        loadDoctorsToDb();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment, new EntryFragment())
                .commit();

    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
    }

    public void appendWinbar(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.winbar_fragment, new WinbarFragment())
                .commit();
    }

    public void loadDoctorsToDb(){
        DoctorsDatabase db = DoctorsDatabase.getDoctorsDatabase(this);
        db.DoctorsDao().insertDoctor(new Doctor("Dima", "therapist", "Dog", "05.05.2023", 1000));
        db.DoctorsDao().insertDoctor(new Doctor("Dima", "therapist", "Cat", "05.05.2023", 800));
        db.DoctorsDao().insertDoctor(new Doctor("Bob", "therapist", "Turtle", "07.05.2023", 1500));
        db.DoctorsDao().insertDoctor(new Doctor("Alice", "nutritionist", "Dog", "05.05.2023", 1000));
        db.DoctorsDao().insertDoctor(new Doctor("Zoy", "therapist", "Cat", "08.05.2023", 1800));
        db.DoctorsDao().insertDoctor(new Doctor("Bob", "nutritionist", "Turtle", "05.05.2023", 1500));
        db.DoctorsDao().insertDoctor(new Doctor("Zoy", "therapist", "Turtle", "09.05.2023", 1700));
        db.DoctorsDao().insertDoctor(new Doctor("Dima", "nutritionist", "Cat", "05.05.2023", 800));
        db.DoctorsDao().insertDoctor(new Doctor("Bob", "therapist", "Dog", "10.05.2023", 2000));
    }

}