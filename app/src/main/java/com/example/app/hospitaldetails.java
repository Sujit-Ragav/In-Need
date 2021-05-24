package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.Database.Userhelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class hospitaldetails extends AppCompatActivity {

    EditText bedsavai, oxyavai, covpoatients, nodoctors, ambulance;
    String userid;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("userid");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitaldetails);


        bedsavai = findViewById(R.id.bedsavai);
        oxyavai = findViewById(R.id.oxyavai);
        covpoatients = findViewById(R.id.covpatients);
        nodoctors = findViewById(R.id.nodoctors);
        ambulance = findViewById(R.id.ambulance);
        userid = getIntent().getStringExtra("userid");
        showcontent();
    }

    private void showcontent() {

        Query checkuser = FirebaseDatabase.getInstance().getReference("userid");
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bedsavai.setText(snapshot.child(userid).child("noofbed").getValue(String.class));
                oxyavai.setText(snapshot.child(userid).child("oxygenavai").getValue(String.class));
                covpoatients.setText(snapshot.child(userid).child("noofcovidpatients").getValue(String.class));
                nodoctors.setText(snapshot.child(userid).child("noofdoctors").getValue(String.class));
                ambulance.setText(snapshot.child(userid).child("ambulanceavai").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hospitaldetails.this, error.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    public void updatedetails(View view) {
        reference.child(userid).child("noofbed").setValue(bedsavai.getText().toString());
        reference.child(userid).child("oxygenavai").setValue(oxyavai.getText().toString());
        reference.child(userid).child("noofcovidpatients").setValue(covpoatients.getText().toString());
        reference.child(userid).child("noofdoctors").setValue(nodoctors.getText().toString());
        reference.child(userid).child("ambulanceavai").setValue(ambulance.getText().toString());
        Toast.makeText(hospitaldetails.this,"Data updated",Toast.LENGTH_SHORT);
        startActivity(new Intent(hospitaldetails.this,MainActivity.class));

    }
}