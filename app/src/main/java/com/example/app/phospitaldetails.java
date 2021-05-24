package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class phospitaldetails extends AppCompatActivity {

    TextView bedsavai, oxyavai, covpoatients, nodoctors, ambulance;
    String userid;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("userid");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phospitaldetails);

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
                Toast.makeText(phospitaldetails.this, error.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    public void home(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}