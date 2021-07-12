package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity<FirebaseStore> extends AppCompatActivity {
    private EditText userName,userPassword,userEmail,userPhoneNo;
    private Button regButton;
    private FirebaseAuth mAuth;
    private Task task;
    private FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // getSupportActionBar().setTitle("Home");
      //  userName=(EditText)findViewById(R.id.etUserName);
       // userPhoneNo=(EditText)findViewById(R.id.etphone);
        userPassword=(EditText)findViewById(R.id.etUserPassword);
        userEmail=(EditText)findViewById(R.id.etUserEmail);
        regButton=(Button)findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(userName.getText().toString().isEmpty()) {
//                    userName.setError("Field Required");
//                    return;
//                }
//                else if (!userName.getText().toString().matches("^[a-zA-Z ]*$")){
//                    userName.setError("Please Inter a valid Name");
//                    return;
//
//                }
//                else {
//                    userName.setError(null);
//                }
                if(userEmail.getText().toString().isEmpty()) {
                    userEmail.setError("Field Required");
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()){
                    userEmail.setError("Please enter a valid Email");
                }
                else
                {
                    userEmail.setError(null);
                }
                if (userPassword.getText().toString().isEmpty()) {
                    userPassword.setError("Field Required");
                    return;
                }

                else {
                    userPassword.setError(null);
                }
//                if(userPhoneNo.getText().toString().isEmpty()){
//                    userPhoneNo.setError("field Required");
//                    return;
//                }
//                else if(!userPhoneNo.getText().toString().matches("[0-9]{10}")){
//                    userPhoneNo.setError("Enter a Valid Phone number");
//                    return;
//                }


                    mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegistrationActivity.this,
                                                "Registration successful,Please check Your email",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                                        mAuth.getInstance().signOut();
                                    }
                                    //addUser();
                                }
                            });

                        }
                    }
                });

            }
        });
    }

    private void addUser() {

       String FullName= userName.getText().toString().trim();
        String Phone=userPhoneNo.getText().toString().trim();
        String Email=userEmail.getText().toString().trim();
        Map<String,Object>userMap=new HashMap<>();
        userMap.put("name",FullName);
        userMap.put("phone",Phone);
        userMap.put("email",Email);
        mFirestore.collection("User").add(userMap);
//        member.setName(FullName);;
//        member.setEmail(Email);
//        member.setPhone(Phone);
//        reff.push().setValue(member);

    }
//    private void addUser(){
//        String FullName= userName.getText().toString().trim();
//        String Phone=userPhoneNo.getText().toString().trim();
//        String Email=userEmail.getText().toString().trim();
//        member.setName(FullName);
//        member.setEmail(Email);
//        member.setPhone(Phone);
//        reff.push().setValue(member);
//        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
//        mAuth.getInstance().signOut();


}
