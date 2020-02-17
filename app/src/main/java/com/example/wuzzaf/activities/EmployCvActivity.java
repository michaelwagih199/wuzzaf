package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.EmployFirestoreManager;
import com.example.wuzzaf.entities.EmplyCv;

public class EmployCvActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, age, phone, collage, degee, grade;
    Button btnSave;
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
        employFirestoreManager = EmployFirestoreManager.newInstance();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnSaveEmployCv:
                    saveEmployCv();
                    break;
            }
        }
    }

    private void saveEmployCv() {
        employFirestoreManager.sendContactsBulk(
                name.getText().toString(),
                age.getText().toString(),
                phone.getText().toString(),
                collage.getText().toString(),
                degee.getText().toString(),
                grade.getText().toString());


    }
}
