package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText hospitalnameedittext;
    RecyclerView recyclerView;
    ArrayList<String> hnamelist;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("userid");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        hospitalnameedittext = findViewById(R.id.Hospital);
        recyclerView = findViewById(R.id.recyclerview);
        hnamelist = new ArrayList<>();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setCheckedItem(R.id.Home);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        hospitalnameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    hnamelist.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigatoinItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.login:
                Intent intent = new Intent(MainActivity.this, hospitallogin.class);
                startActivity(intent);
                break;
            case R.id.regis:
                Intent intent1 = new Intent(MainActivity.this, hospitalregis.class);
                startActivity(intent1);
                break;
            case R.id.Home:
                Toast.makeText(this, "In Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                super.onBackPressed();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setAdapter(String searchedString) {

        databaseReference.child("userid").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                hnamelist.clear();
                recyclerView.removeAllViews();
                int counter = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String name = snapshot.child("hname").getValue(String.class);

                    if (name.toLowerCase().contains(searchedString.toLowerCase())) {
                        hnamelist.add(name);
                        counter++;
                    }
                    if (counter == 15)
                        break;
                }
                searchAdapter = new SearchAdapter(MainActivity.this, hnamelist);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

