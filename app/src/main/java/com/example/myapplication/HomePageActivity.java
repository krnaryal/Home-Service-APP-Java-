package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity {
    private Button btnBike,btnAc,btnPlumb,btnBikeWash,btnElectricity;

    private FirebaseAuth mAuth;
    private androidx.appcompat.widget.Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mainToolbar=(Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Sajilo");
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null) {
            btnBike = (Button) findViewById(R.id.btnbikeservice);
            btnAc = (Button) findViewById(R.id.btnACreparing);
            btnBikeWash = (Button) findViewById(R.id.button7);
            btnElectricity = (Button) findViewById(R.id.btnelectric);
            btnPlumb = (Button) findViewById(R.id.btnplumbing);


            init();
        }

    }

    private void init() {

        btnBike .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,Booking.class));
            }
        });
        btnPlumb .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,history.class));
            }
        });
        btnBikeWash .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,Booking.class));
            }
        });
        btnAc .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,Booking.class));
            }
        });
        btnElectricity .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,Booking.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout_btn:
                mAuth.signOut();
                Intent loginIntent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(loginIntent);
                finish();
                return true;

            case R.id.action_feedback_btn:

                Intent settingsIntent = new Intent(HomePageActivity.this, Feedback.class);
                startActivity(settingsIntent);

                return true;

            case R.id.action_userinfo_btn:

                Intent userIntent = new Intent(HomePageActivity.this, userInfo.class);
                startActivity(userIntent);

                return true;



            default:
                return false;


        }

    }

}

