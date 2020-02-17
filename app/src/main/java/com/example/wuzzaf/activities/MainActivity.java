package com.example.wuzzaf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wuzzaf.R;
import com.example.wuzzaf.dao.UsersFirestoreManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
