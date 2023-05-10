package com.example.petdoc.ui.registration;

import android.content.SharedPreferences;
import android.os.Bundle;
import static com.example.petdoc.MainActivity.USERID;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.ui.entry.EntryFragment;
import com.example.petdoc.ui.pets.PetsFragment;

import java.util.Random;


public class RegistrationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        Button goButton = (Button) v.findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
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