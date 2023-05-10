package com.example.petdoc.ui.entry;

import static com.example.petdoc.MainActivity.USERID;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petdoc.ui.registration.RegistrationFragment;
import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.ui.pets.PetsFragment;

import java.util.Random;


public class EntryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String registred = sharedPreferences.getString("registred", "0");
        if (registred.equals("1")){
            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.appendWinbar();
            mainActivity.changeFragment(new PetsFragment());
        }


        Button regButton = (Button) v.findViewById(R.id.registration_button);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new RegistrationFragment());
            }
        });

        Button goButton = (Button) v.findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                setUSERID();
                mainActivity.appendWinbar();
                mainActivity.sendInTouch();
                mainActivity.changeFragment(new PetsFragment());

            }
        });

        return v;
    }

    public void setUSERID(){
        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EntryFragment()).commit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        USERID = new Random().nextInt(1000000) + 2;
        editor.putInt("id", USERID);
        editor.commit();
    }


}