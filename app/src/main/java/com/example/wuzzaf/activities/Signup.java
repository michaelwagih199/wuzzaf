package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.UsersFirestoreManager;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;
import com.example.wuzzaf.helpers.ToastMessage;
import com.google.firebase.firestore.FirebaseFirestore;


public class Signup extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore db;
    private EditText etfirstName, etlastName, etpassword, etUserName, comfirmPassword;
    private RadioGroup rgAccountType;
    private RadioButton rbAccountType;
    private RadioButton rBcompany;
    private Button btnSignUp;

    private UsersFirestoreManager usersFirestoreManager;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usersFirestoreManager = UsersFirestoreManager.newInstance();

        etfirstName = (EditText) findViewById(R.id.etFirstNameSignUp);
        etlastName = (EditText) findViewById(R.id.etLastNameSignUp);
        etpassword = (EditText) findViewById(R.id.etPasswordSignUp);
        comfirmPassword = (EditText) findViewById(R.id.etConfirmPasswordSignUp);
        etUserName = (EditText) findViewById(R.id.etUserNameSignUp);
        rgAccountType = (RadioGroup) findViewById(R.id.rgAccountTypeSignUp);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

    }

    public void signUp() {
        if (validate()) {
            try {
                usersFirestoreManager.sendContactsBulk(
                        etfirstName.getText().toString(),
                        etlastName.getText().toString(),
                        etUserName.getText().toString(),
                        etpassword.getText().toString(),
                        getRadioButton()
                );


            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent i = new Intent(this, Login.class);
            startActivity(i);

        }

    }

    String getRadioButton() {

        String accountType = "m";
        try {
            int selectedId = rgAccountType.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            rbAccountType = (RadioButton) findViewById(selectedId);
            if (rbAccountType.getText().toString().equals("Candidate")) {
                accountType = "Candidate";

            } else if (rbAccountType.getText().toString().equals("Company")) {
                accountType = "Company";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accountType;
    }

    boolean validate() {
        boolean result = false;
        if (getRadioButton().equals("m")) {
            ToastMessage.addMessage("please chose your accountType", this);
        } else if (etUserName.getText().toString().isEmpty() &&
                etfirstName.getText().toString().isEmpty() && etlastName.getText().toString().isEmpty()) {
            ToastMessage.addMessage("please add all data", this);
        } else if (!etpassword.getText().toString().equals(comfirmPassword.getText().toString())) {
            ToastMessage.addMessage("check password", this);
        } else
            result = true;

        return result;
    }


    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnSignUp:
                    signUp();
                    break;
            }
        }
    }
}
