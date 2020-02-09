package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wuzzaf.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgAccountType;
    private RadioButton rbAccountType;
    private RadioButton rBcompany;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rgAccountType = (RadioGroup) findViewById(R.id.rgAccountType);
//        rBcandidate = (RadioButton) findViewById(R.id.rbCandidate);
//        rBcompany = (RadioButton) findViewById(R.id.rbCompany);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    // get selected radio button from radioGroup
                    int selectedId = rgAccountType.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    rbAccountType = (RadioButton) findViewById(selectedId);
                    if (rbAccountType.getText().toString().equals("Candidate")) {
                        Intent i = new Intent(this, CandidatMain.class);
                        startActivity(i);

                    } else if (rbAccountType.getText().toString().equals("Company")) {
                        Intent i = new Intent(this, CompanyMain.class);
                        startActivity(i);
                    }

                    finish();
                    break;
            }

        }


    }
}
