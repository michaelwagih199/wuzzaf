package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.JopRequirementsManager;
import com.example.wuzzaf.entities.JopRequirements;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;
import com.example.wuzzaf.helpers.ToastMessage;

public class CompanyRequirment extends AppCompatActivity implements View.OnClickListener {

    private JopRequirementsManager jopRequirementsManager;
    EditText age, degee, experinceYear, jopDescription,etJopTitleRequire;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    Button btnSave;
    String userName;
    TextView txtVId, txtVUpload;
    Spinner collage, grade;
    String gradeVal, collageVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_requirment);
        age = (EditText) findViewById(R.id.etAgeRequire);
        collage = (Spinner) findViewById(R.id.etCollegeRequire);
        degee = (EditText) findViewById(R.id.etDegreeRequire);
        experinceYear = (EditText) findViewById(R.id.etExperienceYearRequire);
        jopDescription = (EditText) findViewById(R.id.etJopDescriptionRequire);
        grade = (Spinner) findViewById(R.id.etGradeRequire);
        btnSave = (Button) findViewById(R.id.btnSaveRequirement);
        btnSave.setOnClickListener(this);
        txtVId = (TextView) findViewById(R.id.txtRequireVId);
        etJopTitleRequire = (EditText)findViewById(R.id.etJopTitleRequire);

        userName = sharedPrefrenceHelper.getUsername(this);
        jopRequirementsManager = JopRequirementsManager.newInstance();
        jopRequirementsManager.popUI(userName,
                jopDescription,
                etJopTitleRequire,
                age,
                collage,
                degee,
                experinceYear,
                grade,
                btnSave,
                txtVId,
                this);

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
                case R.id.btnSaveRequirement:
                    if (btnSave.getText().equals("Save")) {
                        saveJopRequire();
                    } else if (btnSave.getText().equals("Update")) {
                        updateRequireJop();
                    }

                    break;

            }
        }
    }


    private void saveJopRequire() {
        try {

            jopRequirementsManager.sendContactsBulk(collageVal,
                    degee.getText().toString(),
                    gradeVal,
                    experinceYear.getText().toString(),
                    age.getText().toString(),
                    etJopTitleRequire.getText().toString(),
                    jopDescription.getText().toString(),
                    userName);

            ToastMessage.addMessage("Saved successfull", this);
            Intent i = new Intent(this, CompanyMain.class);
            startActivity(i);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateRequireJop() {
        try {
            JopRequirements jopRequirements = new JopRequirements(collageVal,
                    degee.getText().toString(),
                    gradeVal,
                    experinceYear.getText().toString(),
                    age.getText().toString(),
                    etJopTitleRequire.getText().toString(),
                    jopDescription.getText().toString(),
                    userName);

            ToastMessage.addMessage("Saved successfull", this);
            jopRequirementsManager.updateContact(jopRequirements, txtVId.getText().toString());
            Intent i = new Intent(this, CompanyMain.class);
            startActivity(i);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
