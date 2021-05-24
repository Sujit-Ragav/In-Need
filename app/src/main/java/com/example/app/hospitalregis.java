package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

   

public class hospitalregis extends AppCompatActivity {

    TextInputLayout name, phoneno, userid, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitalregis);

        name = findViewById(R.id.hospitalname);
        phoneno = findViewById(R.id.phoneno);
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);


    }

    public void signup(View view) {

        if(!vhname()| !vuname()| !vpassword()| !vrepassword()| !vphoneno()){
            return;
        }
        String hospitalname = name.getEditText().getText().toString().trim();
        String  user= userid.getEditText().getText().toString().trim();
        String phone=phoneno.getEditText().getText().toString().trim();
        String passw= password.getEditText().getText().toString().trim();


        Intent intent = new Intent(hospitalregis.this, Verification.class);
        intent.putExtra("hname",hospitalname);
        intent.putExtra("userid",user);
        if(phone.charAt('0')!='+')
           phone="+91"+phone;
        intent.putExtra("phoneno",phone);
        intent.putExtra("password",passw);
    }


    private boolean vhname() {
        String val = name.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            name.setError("Empty");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }

    }

    private boolean vuname() {
        String val = userid.getEditText().getText().toString().trim();
        String checkspaces="\\A\\w{1,10}\\z";
        if (val.isEmpty()) {
            userid.setError("Empty");
            return false;
        } else if(val.length()>20){
            userid.setError("Userid more than 20 character");
            return false;

        }
        else if (!val.matches(checkspaces)) {
            userid.setError("No Whitespaces allowed");
            return false;
        }
        else{
            userid.setError(null);
            userid.setErrorEnabled(false);
            return true;
        }

    }

    private boolean vpassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkspaces="^"+"(?=.*[a-zA-Z])"+"(?=.*[@#$%^&*!+=()])"+"(?=.\\S+$)"+".{6,}"+"$";
        if (val.isEmpty()) {
            password.setError("Empty");
            return false;
        }
        else if (!val.matches(checkspaces)) {
            password.setError("No Whitespaces allowed");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    private boolean vrepassword(){
        String valpassword=password.getEditText().getText().toString().trim();
        String valrepassword=repassword.getEditText().getText().toString().trim();
        if(!valpassword.matches(valrepassword)){
            repassword.setError("Password does not match");
            return false;
        }
        else{
            repassword.setError(null);
            repassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean vphoneno(){
        String val=phoneno.getEditText().getText().toString().trim();
        if(val.length() != 10){
            phoneno.setError("Enter a valid phone no.");
            return false;
        }
        else{
            phoneno.setError(null);
            phoneno.setErrorEnabled(false);
            return true;
        }
    }


}