package com.example.wuzzaf.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.CompanyAcceptOrderManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.example.wuzzaf.helpers.ToastMessage

class EmployResult : AppCompatActivity(), View.OnClickListener {
    var userName: String? = "m"
    lateinit var companyAcceptOrderManager: CompanyAcceptOrderManager
    lateinit var mcontext: Context
    lateinit var txtVResult: TextView
    lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employ_result)
        mcontext = this
        txtVResult = findViewById(R.id.tvEmployCvResult)
        btnBack = findViewById(R.id.btnEmpResult)
        btnBack.setOnClickListener(this)
        companyAcceptOrderManager = CompanyAcceptOrderManager.newInstance()
        val key = SharedPrefrenceHelper()
        userName = key.getUsername(this)
        ToastMessage.addMessage(userName, this)
        companyAcceptOrderManager.pupUi(txtVResult, userName)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.getId()) {
                R.id.btnEmpResult -> {
                    val i = Intent(this, CandidatMain::class.java)
                    startActivity(i)
                    finish()
                }

            }
        }
    }
}