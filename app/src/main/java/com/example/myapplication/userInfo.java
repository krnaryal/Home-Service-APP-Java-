package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class userInfo extends AppCompatActivity {
    private TextView a,b,c;
    private FirebaseAuth mAuth;
   private FirebaseFirestore firebaseFirestore;
   private FirebaseUser firebaseUser;
   private Button up;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        a=(TextView) findViewById(R.id.tvName);
        b=(TextView) findViewById(R.id.tvPhone);
        up=(Button)findViewById(R.id.btnupdate);
        firebaseFirestore=FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        final String userid = mAuth.getCurrentUser().getUid();

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName= a.getText().toString().trim();
                String Phone=b.getText().toString().trim();
                Map<String,Object> userMap=new HashMap<>();
                userMap.put("name",FullName);
                userMap.put("phone",Phone);
                firebaseFirestore.collection("User").document(userid).set(userMap);
            }
        });



//            firebaseFirestore.collection("User").addSnapshotListener(new EventListener<QuerySnapshot>() {
//                @Override
//                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                    for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
//                        String isAttendance = documentChange.getDocument().getData().get("name").toString();
//                        String isCalender = documentChange.getDocument().getData().get("email").toString();
//                        String isEnablelocation = documentChange.getDocument().getData().get("phone").toString();
//                        a.setText(isAttendance);
//                        b.setText(isCalender);
//                        c.setText(isEnablelocation);
//                    }
//                    Toast.makeText(userInfo.this,userid,Toast.LENGTH_SHORT).show();
//                }
//
//            });


        firebaseFirestore.collection("User").document(userid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()) {
                    String name = task.getResult().getString("name");
                    String phone=task.getResult().getString("phone");
                    b.setText(phone);
                    a.setText(name);
                }
            }
        });

    }
}
