package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wuzzaf.R;

public class CandidatMain extends AppCompatActivity implements View.OnClickListener {
    Button btnEmployCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidat_main);
        btnEmployCv = (Button) findViewById(R.id.btnEmployCv);
        btnEmployCv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnEmployCv:
                    Intent i = new Intent(this, EmployCvActivity.class);
                    startActivity(i);
                    break;
            }
        }
    }
}
