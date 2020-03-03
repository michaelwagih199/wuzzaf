package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.EmployFirestoreManager;
import com.example.wuzzaf.entities.EmplyCv;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;
import com.example.wuzzaf.helpers.ToastMessage;

import java.io.File;

public class EmployCvActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, age, phone, degee,etExperienceYearEmploy;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    Button btnSave;
    String userName;
    TextView txtVId, txtVUpload;
    Spinner collage, grade;
    String gradeVal, collageVal;
    int PICK_IMAGE_REQUEST = 1212;

    private EmployFirestoreManager employFirestoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_cv);
        name = (EditText) findViewById(R.id.etNameEmploy);
        age = (EditText) findViewById(R.id.etAgeEmploy);
        phone = (EditText) findViewById(R.id.etPhoneEmploy);
        collage = (Spinner) findViewById(R.id.etCollegeEmploy);
        degee = (EditText) findViewById(R.id.etDegreeEmploy);
        grade = (Spinner) findViewById(R.id.etGradeEmploy);
        btnSave = (Button) findViewById(R.id.btnSaveEmployCv);
        btnSave.setOnClickListener(this);
        etExperienceYearEmploy = (EditText)findViewById(R.id.etExperienceYearEmploy);
        txtVId = (TextView) findViewById(R.id.txtVcVId);
        userName = sharedPrefrenceHelper.getUsername(this);
        txtVUpload = (TextView) findViewById(R.id.txtVUpload);
        txtVUpload.setOnClickListener(this);
        employFirestoreManager = EmployFirestoreManager.newInstance();
        employFirestoreManager.popUI(userName, name, age, phone, etExperienceYearEmploy, collage, degee, grade, btnSave, txtVId, this);

        grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                gradeVal = grade.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        collage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                collageVal = collage.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.btnSaveEmployCv:
                    if (btnSave.getText().equals("Save")) {
                        saveEmployCv();
                    } else if (btnSave.getText().equals("Update")) {
                        updateCv();
                    }

                    break;
                case R.id.txtVUpload:
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    intent.setType("application/pdf");
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
                    collageVal,
                    degee.getText().toString(),
                    gradeVal,
                    userName,
                    etExperienceYearEmploy.getText().toString()
            );
            ToastMessage.addMessage("Saved successfull", this);
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
                    collageVal,
                    degee.getText().toString(),
                    gradeVal,
                    userName,
                    etExperienceYearEmploy.getText().toString());
            ToastMessage.addMessage("Saved successfull", this);
            employFirestoreManager.updateContact(emplyCv, txtVId.getText().toString());
            Intent i = new Intent(this, CandidatMain.class);
            startActivity(i);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }






}
