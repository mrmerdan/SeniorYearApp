package com.example.senioryearapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.senioryearapp.locationEntry;

public class locationDetail extends AppCompatActivity {

    private FirebaseFirestore database;
    private Intent intent;
    private locationEntry location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location_detail);

        database = FirebaseFirestore.getInstance();
        intent = getIntent();
        location = (locationEntry) intent.getSerializableExtra("location");

        TextView name = findViewById(R.id.nameEdit);
        name.setText(location.name);

        TextView time = findViewById(R.id.timeEdit);
        time.setText(location.time);

        TextView phoneNumber = findViewById(R.id.frequencyEdit);
        phoneNumber.setText(location.frequency);
    }

    public void updateButton_onClick(View view){

        TextView name = findViewById(R.id.nameEdit);
        TextView time = findViewById(R.id.timeEdit);
        TextView frequency = findViewById(R.id.frequencyEdit);


        locationEntry c = new locationEntry(name.getText().toString(), time.getText().toString(), frequency.getText().toString());
        DocumentReference ref = database.collection("Locations").document(location.id);
        c.id = location.id;
        ref.set(c);

        finish();
    }

    public void deleteButton_onClick(View view){

        database.collection("Locations").document(location.id).delete();

        finish();
    }
}
