package com.example.wuzzaf.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.EmployFirestoreManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.example.wuzzaf.helpers.ToastMessage

class CandidatMain : AppCompatActivity(), View.OnClickListener {
    private lateinit var employFirestoreManager: EmployFirestoreManager
    lateinit var userName: String
    lateinit var mContext: Context
    var btnEmployCv: Button? = null
    var btnJobSuggest: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_main)
        btnEmployCv = findViewById<View>(R.id.btnEmployCv) as Button
        btnEmployCv!!.setOnClickListener(this)
        btnJobSuggest = findViewById<View>(R.id.btnJobSuggest) as Button
        btnJobSuggest!!.setOnClickListener(this)
        employFirestoreManager = EmployFirestoreManager.newInstance()
        mContext = this

       // ToastMessage.addMessage(userName,this)
    }

    override fun onClick(v: View) {
        if (v != null) {
            when (v.id) {

                R.id.btnEmployCv -> {
                    val i = Intent(this, EmployCvActivity::class.java)
                    startActivity(i)
                    finish()
                }

                R.id.btnJobSuggest -> {
                    val intent = Intent(this, JopSugisition::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}