package com.example.petdoc.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;


public class WinbarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_winbar, container, false);

        ImageButton priceButton = (ImageButton) v.findViewById(R.id.prices_button);
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PricesFragment());
            }
        });

        ImageButton appointmentsButton = (ImageButton) v.findViewById(R.id.appointments_button);
        appointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new AppointmentsFragment());
            }
        });

        ImageButton petsButton = (ImageButton) v.findViewById(R.id.pets_button);
        petsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetsFragment());
            }
        });

        ImageButton chatButton = (ImageButton) v.findViewById(R.id.chat_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new ChatFragment());
            }
        });
        return v;
    }

}