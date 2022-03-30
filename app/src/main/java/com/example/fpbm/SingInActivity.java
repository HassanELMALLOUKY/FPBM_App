package com.example.fpbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class SingInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button connexion;
    private EditText editTextCNE, editTextPassword;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        mAuth= FirebaseAuth.getInstance();

        editTextCNE= findViewById(R.id.num_apogee_et);
        editTextPassword= findViewById(R.id.Cin_et);
        progressBar= (ProgressBar) findViewById(R.id.progressSingIn);



        connexion= findViewById(R.id.button_login);
        connexion.setOnClickListener(this);




    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SingInActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        String cneEtd = editTextCNE.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (cneEtd.isEmpty()) {
            editTextCNE.setError("Email is required");
            editTextCNE.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        cneEtd = cneEtd + "@gmail.com";
        //mAuth.signInAnonymously()
        mAuth.signInWithEmailAndPassword(cneEtd, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Intent intent = new Intent(SingInActivity.this, MainActivity.class);
                    progressBar.setVisibility(View.GONE);
                    startActivity(intent);
                    SingInActivity.this.finish();

                } else {
                    Toast.makeText(SingInActivity.this, "Failed to login! Incorrect cne or password or check your connexion", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
}