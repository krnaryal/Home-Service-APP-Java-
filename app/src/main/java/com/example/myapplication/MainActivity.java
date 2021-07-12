package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView userregis;
    private FirebaseAuth mAuth;
    private TextView ForgotPassword;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        userregis = findViewById(R.id.tvRegister);
        ForgotPassword = findViewById(R.id.tvForgotPassword);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
        }
        userregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));

            }
        });
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, forgotPassword.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().isEmpty()){
                    Email.setError("Email is required ");
                    return;
            }else if(!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()){
                    Email.setError("Please Inter a valid Email");
                }
                else {
                    Email.setError(null);
                }
                if(Password.getText().toString().isEmpty()){
                    Password.setError("password is required ");
                    return;
                }else {
                    Password.setError(null);
                }
              mAuth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().
                      toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()) {
                          if (mAuth.getCurrentUser().isEmailVerified()) {
                              startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                          }
                          //startActivity(new Intent(MainActivity.this,Navigation.class));
                          else {
                              Toast.makeText(MainActivity.this, "Please verify Your Account", Toast.LENGTH_SHORT).show();
                          }
                      }
                      else{
                          Toast.makeText(MainActivity.this,"Invalid user Info",Toast.LENGTH_SHORT).show();
                      }
                      }
              });
            }
        });

    }
}


