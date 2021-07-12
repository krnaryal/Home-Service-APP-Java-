package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity {
    EditText Location;
    EditText Name;
    EditText PhoneNo;
    Spinner Error;
    Button Submit;
    DatabaseReference databaseArtists;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        databaseArtists = FirebaseDatabase.getInstance().getReference("Booking");
        Location = (EditText) findViewById(R.id.etLocation);
        Name = (EditText) findViewById(R.id.editBookName);
        PhoneNo = (EditText) findViewById(R.id.etphone);
        Error = (Spinner) findViewById(R.id.spinner);
        Submit = (Button) findViewById(R.id.btsubmitbooking);
        mAuth = FirebaseAuth.getInstance();
        final String userid=mAuth.getCurrentUser().getUid();
        firebaseFirestore=FirebaseFirestore.getInstance();

        firebaseFirestore.collection("User").document(userid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()) {
                    String name = task.getResult().getString("name");
                    String phone=task.getResult().getString("phone");
                    PhoneNo.setText(phone);
                    Name.setText(name);
                }
            }
        });


//      String userId=mAuth.getCurrentUser().getUid();
//      databaseArtists=FirebaseDatabase.getInstance().getReference().child(userId);
//
//      databaseArtists.addValueEventListener(new ValueEventListener() {
//          @Override
//          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//              Member member=dataSnapshot.getValue(Member.class);
//              Name.setText(member.getName());
//              PhoneNo.setText(member.getPhone());
//          }
//
//          @Override
//          public void onCancelled(@NonNull DatabaseError databaseError) {
//
//          }
//      });


                    Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Location.getText().toString().isEmpty()) {
                        Location.setError("field Required");
                        return;

                    } else if (!Location.getText().toString().matches("[a-z,A-Z]*")) {
                        Location.setError("Enter A valid location");
                    } else {
                        Location.setError(null);
                    }
                    if (Name.getText().toString().isEmpty()) {
                        Name.setError("Field Required");
                        return;
                    } else if (!Name.getText().toString().matches("[a-z,A-Z]*")) {
                        Name.setError("Please Enter a valid Name");
                    } else {
                        Name.setError(null);
                    }
                    if (PhoneNo.getText().toString().isEmpty()) {
                        PhoneNo.setError("field Required");
                        return;
                    } else if (!PhoneNo.getText().toString().matches("[0-9]{10}")) {
                        PhoneNo.setError("Enter a Valid Phone number");
                        return;
                    }

                    final String name=Name.getText().toString().trim();
                    final String error=Error.getSelectedItem().toString();
                    final String phone=PhoneNo.getText().toString().trim();
                    final String location=Location.getText().toString();

                    Map<String,Object> postMap= new HashMap<>();

                    postMap.put("name",name);
                    postMap.put("phone",phone);
                    postMap.put("location",location);
                    postMap.put("error",error);
                    postMap.put("userid",userid);

                    firebaseFirestore.collection("Book").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Booking.this,"Successful",Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                }
            });

        }



}
