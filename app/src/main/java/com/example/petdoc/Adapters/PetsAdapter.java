package com.example.petdoc.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.Repositories.Pet;

import java.util.List;

public class PetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Pet> pets;
    public PetsAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.pet, parent, false)
        ){};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView nText = holder.itemView.findViewById(R.id.nameText);
        TextView nAge = holder.itemView.findViewById(R.id.ageText);
        TextView nType = holder.itemView.findViewById(R.id.typeText);
        Log.i("MYtag", ""+pets.get(position));
        nText.setText(pets.get(position).getName());
        nAge.setText(String.valueOf(pets.get(position).getAge())+" years");
        nType.setText(pets.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }
}