package com.example.petdoc.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;


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
                mainActivity.appendWinbar();
                mainActivity.changeFragment(new PetsFragment());
                /*
                Log.i("MYtag", "kek");
                EditText n = v.findViewById(R.id.editTextLogin);
                Log.i("MYtag", "lol");
                String login = n.getText().toString();
                String password = ((EditText)v.findViewById(R.id.editTextPassword)).getText().toString();
                String mail = ((EditText)v.findViewById(R.id.editTextMail)).getText().toString();
                String phone = ((EditText)v.findViewById(R.id.editTextPhone)).getText().toString();
                Log.i("MYtag", "lol");
                if (createUser(login, password, mail, phone)){
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.appendWinbar();
                    mainActivity.changeFragment(new PetsFragment());
                } else {
                    Toast.makeText(getActivity(), "Неверно введены данные",Toast.LENGTH_LONG).show();
                } */
            }
        });

        return v;
    }

    private boolean createUser(String login, String password, String mail, String phone) {
        Log.i("MYtag", "lol");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("registred", "1");
        editor.putString("login", login);
        editor.putString("password",  password);
        editor.putString("mail", mail);
        editor.putString("phone",  phone);
        editor.commit();
        return true;
    }

}