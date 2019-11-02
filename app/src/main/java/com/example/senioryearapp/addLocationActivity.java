package com.example.senioryearapp;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.DocumentReference;
        import com.example.senioryearapp.locationEntry;

public class addLocationActivity extends AppCompatActivity {

    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_location);
        database = FirebaseFirestore.getInstance();
    }

    public void addNewButton_onClick(View view){

        TextView name = findViewById(R.id.nameEditA);
        TextView time = findViewById(R.id.timeEditA);
        TextView frequency = findViewById(R.id.frequencyEditA);


        locationEntry c = new locationEntry(name.getText().toString(), time.getText().toString(), frequency.getText().toString());

        //Here instead of adding directly we are first getting a reference so we save the ID;
        // this is not necessary but it will make life easier latter when editing/deleting.

        DocumentReference ref = database.collection("Locations").document();
        c.id = ref.getId();
        ref.set(c);

        //Finishes the activity and return to the parent one.

        finish();
    }
}
