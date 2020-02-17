package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.UsersFirestoreManager;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgAccountType;
    private RadioButton rbAccountType;
    private RadioButton rBcompany;
    private Button btnLogin;
    TextView txtVSignIn;
    private EditText etUserName, etPassword;
    private UsersFirestoreManager usersFirestoreManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rgAccountType = (RadioGroup) findViewById(R.id.rgAccountType);
//        rBcandidate = (RadioButton) findViewById(R.id.rbCandidate);
//        rBcompany = (RadioButton) findViewById(R.id.rbCompany);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txtVSignIn = (TextView) findViewById(R.id.txtVSignIn);
        txtVSignIn.setOnClickListener(this);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        usersFirestoreManager = UsersFirestoreManager.newInstance();

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    try {
                        // get selected radio button from radioGroup
                        int selectedId = rgAccountType.getCheckedRadioButtonId();
                        // find the radiobutton by returned id
                        rbAccountType = (RadioButton) findViewById(selectedId);
                        if (!rbAccountType.getText().toString().isEmpty()) {
                            usersFirestoreManager.isUser(etUserName.getText().toString(),
                                    etPassword.getText().toString(),
                                    rbAccountType.getText().toString(),
                                    this);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.txtVSignIn:
                    Intent i = new Intent(this, Signup.class);
                    startActivity(i);
                    break;

            }

        }


    }
}
