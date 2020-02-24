package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.EmployFirestoreManager;
import com.example.wuzzaf.entities.EmplyCv;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;

public class EmployCvActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, age, phone, collage, degee, grade;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    Button btnSave;
    String userName;
    TextView txtVId;

    private EmployFirestoreManager employFirestoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_cv);
        name = (EditText) findViewById(R.id.etNameEmploy);
        age = (EditText) findViewById(R.id.etAgeEmploy);
        phone = (EditText) findViewById(R.id.etPhoneEmploy);
        collage = (EditText) findViewById(R.id.etCollegeEmploy);
        degee = (EditText) findViewById(R.id.etDegreeEmploy);
        grade = (EditText) findViewById(R.id.etGradeEmploy);
        btnSave = (Button) findViewById(R.id.btnSaveEmployCv);
        btnSave.setOnClickListener(this);
        txtVId = (TextView) findViewById(R.id.txtVcVId);
        userName = sharedPrefrenceHelper.getUsername(this);
        employFirestoreManager = EmployFirestoreManager.newInstance();
        employFirestoreManager.popUI(userName,name,age,phone,collage,degee,grade,btnSave,txtVId,this);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnSaveEmployCv:
                    if (btnSave.getText().equals("Save")){
                        saveEmployCv();
                    }else if(btnSave.getText().equals("Update")){
                        updateCv();
                    }

                    break;
            }
        }
    }

    private void saveEmployCv() {
        try {
            employFirestoreManager.sendContactsBulk(
                    name.getText().toString(),
                    age.getText().toString(),
                    phone.getText().toString(),
                    collage.getText().toString(),
                    degee.getText().toString(),
                    grade.getText().toString(),
                    userName
            );
            Intent i = new Intent(this, CandidatMain.class);
            startActivity(i);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateCv() {
        try {
            EmplyCv emplyCv = new EmplyCv(name.getText().toString(),
                    age.getText().toString(),
                    phone.getText().toString(),
                    collage.getText().toString(),
                    degee.getText().toString(),
                    grade.getText().toString(),
                    userName);
            employFirestoreManager.updateContact(emplyCv,txtVId.getText().toString());
            Intent i = new Intent(this, CandidatMain.class);
            startActivity(i);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
