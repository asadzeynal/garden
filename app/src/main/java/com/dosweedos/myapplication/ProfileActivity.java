package com.dosweedos.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonLogOut;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogOut){
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
            firebaseAuth.signOut();
        }

    }
}
