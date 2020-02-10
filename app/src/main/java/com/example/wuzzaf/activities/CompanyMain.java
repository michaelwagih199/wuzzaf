package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wuzzaf.R;

public class CompanyMain extends AppCompatActivity implements View.OnClickListener {
    Button btnCompanyRequirment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);
        btnCompanyRequirment = (Button) findViewById(R.id.btnCompanyRequirement);
        btnCompanyRequirment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnCompanyRequirement:
                    Intent i = new Intent(this, CompanyRequirment.class);
                    startActivity(i);
                    break;


            }


        }

    }
}
