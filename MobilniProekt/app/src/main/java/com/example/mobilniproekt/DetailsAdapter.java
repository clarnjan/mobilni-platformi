package com.example.mobilniproekt;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    ArrayList<String> exersises;

    public DetailsAdapter(ArrayList<String> listaVezbi) {
        exersises=listaVezbi;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView exersise;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            exersise = itemView.findViewById(R.id.TWDetails);
        }
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdetails,parent,false);
        DetailsViewHolder dvh=new DetailsViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        String currentItem=exersises.get(position);
        holder.exersise.setText(currentItem);
    }

    @Override
    public int getItemCount() {
        return exersises.size();
    }
}
