package com.example.senioryearapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.senioryearapp.locationEntry;
import com.example.senioryearapp.infoCardTemplate;


public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = findViewById(R.id.contactList);
        adapter = setUpAdapter(database);

        setUpRecyclerView(recyclerView,adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void addLocationButton_onClick(View view){
        Intent intent = new Intent(MainActivity.this, addLocationActivity.class);
        startActivity(intent);
    }

    //Connects our recycler view with the viewholder (how we want to show our model[data])
    // and the firestore adapter
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    //Creates a Firestore adapter to be used with a Recycler view.
    //We will see adapter in more details after the midterm
    //More info on this: https://github.com/firebase/FirebaseUI-Android/blob/master/firestore/README.md
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db){
        Query query = db.collection("Locations").orderBy("name").limit(50);
        FirestoreRecyclerOptions<locationEntry> options = new FirestoreRecyclerOptions.Builder<locationEntry>()
                .setQuery(query,locationEntry.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<locationEntry,infoCardTemplate>(options){
            //For each item in the database connect it to the view
            @Override
            public void onBindViewHolder(infoCardTemplate holder, int position, final locationEntry model)
            {
                holder.name.setText(model.name);
                holder.time.setText(model.time);
                holder.frequency.setText(model.frequency);


                //Set the on click for the button
                // e.g. setOnClickListener ((View v) -> ....
                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,locationDetail.class);
                        intent.putExtra("location",model);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public infoCardTemplate onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.location_entry,group,false);
                return new infoCardTemplate(view);
            }
        };
        return adapter;
    }
}
