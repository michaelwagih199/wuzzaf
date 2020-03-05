package com.example.wuzzaf.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.EmployApplyOrderManager
import com.example.wuzzaf.dao.JopRequirementsManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.example.wuzzaf.helpers.ToastMessage

class JobSuggestionDetails : AppCompatActivity(), View.OnClickListener {

    private var jopRequirementsManager: JopRequirementsManager? = null
    private var employApplyOrderManager: EmployApplyOrderManager? = null
    private lateinit var mContext: Context
    private lateinit var tvCompanyName: TextView
    lateinit var tvJobTitle: TextView
    lateinit var tvJobDescription: TextView
    lateinit var tvDegree: TextView
    lateinit var tvGradeD: TextView
    lateinit var tvYear: TextView
    lateinit var tvAge: TextView
    lateinit var tvCollegeD: TextView
    lateinit var tvId: TextView
    lateinit var btnApplyJob: Button
    var nodeId :String? ="node"
    var userName :String? ="m"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_suggestion_details)
        mContext = this
        tvCompanyName = findViewById(R.id.tvCompanyNameD)
        tvJobTitle = findViewById(R.id.tvJobTitleD)
        tvJobDescription = findViewById(R.id.tvJobDescriptionD)
        tvDegree = findViewById(R.id.tvDegreeD)
        tvGradeD = findViewById(R.id.tvGradeD)
        tvYear = findViewById(R.id.tvYearD)
        tvAge = findViewById(R.id.tvAgeD)
        tvCollegeD = findViewById(R.id.tvCollegeD)
        tvId = findViewById(R.id.tvIdD)
        btnApplyJob = findViewById(R.id.btnApplyJobD)
        btnApplyJob.setOnClickListener(this)

        jopRequirementsManager = JopRequirementsManager.newInstance()
        employApplyOrderManager = EmployApplyOrderManager.newInstance()

        try {
            val key = SharedPrefrenceHelper()
            userName = key.getUsername(this)
            val bundle: Bundle? = intent.extras
            if (bundle != null) {
                nodeId = bundle.getString("ID")
            }
        } catch (e: Exception) {
        }
        jopRequirementsManager?.pupDetailsUi(nodeId,
                    tvJobDescription,
                    tvCompanyName,
                    tvJobTitle,
                    tvAge,
                    tvCollegeD,
                    tvDegree,
                    tvYear,
                    tvGradeD,
                    tvId,
                    mContext)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.getId()) {
                R.id.btnApplyJobD -> applyJob()

            }
        }
    }

    fun applyJob(){
        employApplyOrderManager!!.sendContactsBulk(nodeId,userName)
        ToastMessage.addMessage("You applied for job",mContext)
        val i = Intent(this, CandidatMain::class.java)
        startActivity(i)
        finish()
    }

}
