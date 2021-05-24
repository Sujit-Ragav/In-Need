package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class hospitallogin extends AppCompatActivity {

    TextInputLayout userid,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitallogin);


        userid=findViewById(R.id.userid);
        password=findViewById(R.id.password);
    }

        public void login (View view){

        if(!vuserid()| !vpassword()){
            return;
        }
        String user=userid.getEditText().getText().toString().trim();
        String pass=password.getEditText().getText().toString().trim();

            Query checkuser= FirebaseDatabase.getInstance().getReference("userid").equalTo(user);
            checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userid.setError(null);
                    userid.setErrorEnabled(false);

                    String systempassword= dataSnapshot.child(user).child("password").getValue(String.class);
                    if(systempassword.equals(pass)){
                        password.setError(null);
                        password.setErrorEnabled(false);

                        Intent intent=new Intent(hospitallogin.this,hospitaldetails.class);
                        intent.putExtra("userid",user);
                    }
                    else {
                        Toast.makeText(hospitallogin.this,"User id does not exist",Toast.LENGTH_SHORT);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(hospitallogin.this, error.getMessage(),Toast.LENGTH_SHORT);
                }
            });


        }

        private boolean vuserid(){
        String val=userid.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            userid.setError("Userid Empty");
            return false;
        }
        else{
            userid.setError(null);
            userid.setErrorEnabled(false);
            return true;
        }
        }

        private boolean vpassword(){
        String val=password.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            password.setError("Empty password");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return false;

        }
        }
    }
