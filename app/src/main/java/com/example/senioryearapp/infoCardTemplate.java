package com.example.senioryearapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.senioryearapp.R;

public class infoCardTemplate extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView time;
    public TextView frequency;
    public Button detailsButton;

    public infoCardTemplate(View view) {
        super(view);
        name = view.findViewById(R.id.contactName);
        time = view.findViewById(R.id.contactTime);
        frequency = view.findViewById(R.id.contactFrequency);
        detailsButton = view.findViewById(R.id.goDetails);

    }
}
