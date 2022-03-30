package com.example.fpbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    private Button buttonStart;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        buttonStart =(Button) findViewById(R.id.butwelcome);

        auth = FirebaseAuth.getInstance();

        firebaseUser = auth.getCurrentUser();


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseUser != null){
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashScreenActivity.this, SingInActivity.class));
                    finish();
                }

            }
        });
    }
}