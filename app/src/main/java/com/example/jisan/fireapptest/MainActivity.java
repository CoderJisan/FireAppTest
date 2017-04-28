package com.example.jisan.fireapptest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText eName,eLocation,eBlood,eContact;
    Button ebtn;


    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName=(EditText) findViewById(R.id.name);
        eLocation=(EditText) findViewById(R.id.location);
        eBlood=(EditText) findViewById(R.id.blood);
        eContact=(EditText) findViewById(R.id.contact);
        ebtn=(Button) findViewById(R.id.btn);
        reference= FirebaseDatabase.getInstance().getReference();

        ebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=eName.getText().toString();
                String location=eLocation.getText().toString();
                String blood=eBlood.getText().toString();
                String contact=eContact.getText().toString();
                HashMap<String,String>dataMap=new HashMap<String, String>();
                dataMap.put("Name",name);
                dataMap.put("Location",location);
                dataMap.put("Blood Type",blood);
                dataMap.put("Contact NO..",contact);
                reference.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
