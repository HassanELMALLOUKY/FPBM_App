package com.example.fpbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class EmploiActivity extends AppCompatActivity {

    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi);

        data = getIntent().getStringExtra("data");

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}