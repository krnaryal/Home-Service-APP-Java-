package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    private EditText ouremail,feedback;
    Button send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ouremail = findViewById(R.id.email);
        feedback = findViewById(R.id.feedback);
        send=findViewById(R.id.btnsend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ourmessage = feedback.getText().toString();
                String ourEmail = ouremail.getText().toString();
                String[] email_divide = ourEmail.split(",");
                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL,email_divide);
                send.putExtra(Intent.EXTRA_TEXT,ourmessage);
                send.setType("message/rfc22");
                send.setPackage("com.google.android.gm");
                startActivity(send);
                /*if(feedback.getText().toString().equals("")){
                      feedback.setError("This Field is Mandatory");
                    }
                      else if(feedback.getText().toString().equals(""))
                    {
                       feedback.setError("This Field Is Mandatory");
                    }else {
                    Intent intent = new Intent(Intent.ACTION_SEND,Uri.parse("To"+Email.getText().toString().trim()));
                    intent.setType("message/html");
                    intent.putExtra(Intent.EXTRA_EMAIL,"To"+Email.getText().toString().trim());
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback To Sajilo App");
                    intent.putExtra(Intent.EXTRA_TEXT, "Name:" + feedback.getText() + "\n Message:" + feedback.getText());
                    try {
                        startActivity(Intent.createChooser(intent, "Please Select Email"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Feedback.this, "There are no Email Clients", Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });
    }
}
