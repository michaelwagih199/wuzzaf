package com.example.wuzzaf.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.CompanyAcceptOrderManager
import com.example.wuzzaf.dao.EmployFirestoreManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.example.wuzzaf.helpers.ToastMessage
import kotlinx.android.synthetic.main.activity_job_suggestion_details.*

class EmploySuggetDetails : AppCompatActivity(), View.OnClickListener {
    lateinit var sharedKey:SharedPrefrenceHelper
    lateinit var mContext: Context
    var nodeId: String? = "node"
    var userName : String? = "userName"
    private lateinit var tvEmployNameD: TextView
    private lateinit var tvEmpAgeD: TextView
    private lateinit var tvEmpPhoneD: TextView
    private lateinit var tvEmpCollegeD: TextView
    private lateinit var tvEmpDegreeD: TextView
    private lateinit var tvEmpYearD: TextView
    private lateinit var tvIdD: TextView
    private lateinit var tvEmpGradeD: TextView
    private lateinit var btnAcceptCv: Button
    private var employManager: EmployFirestoreManager? = null
    private var companyAcceptOrderManager: CompanyAcceptOrderManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emplpoy_sugget_details)
        mContext = this
        tvEmpAgeD = findViewById(R.id.tvEmpAgeD)
        tvEmployNameD = findViewById(R.id.tvEmployNameD)
        tvEmpPhoneD = findViewById(R.id.tvEmpPhoneD)
        tvEmpCollegeD = findViewById(R.id.tvEmpCollegeD)
        tvEmpDegreeD = findViewById(R.id.tvEmpDegreeD)
        tvEmpYearD = findViewById(R.id.tvEmpYearD)
        tvEmpGradeD = findViewById(R.id.tvEmpGradeD)
        tvIdD = findViewById(R.id.tvIdD)
        btnAcceptCv = findViewById(R.id.btnAcceptCv)
        btnAcceptCv.setOnClickListener(this)
        employManager = EmployFirestoreManager.newInstance()
        companyAcceptOrderManager = CompanyAcceptOrderManager.newInstance()

        val bundle: Bundle? = intent.extras
        if (bundle != null)
            nodeId = bundle.getString("ID")
        ToastMessage.addMessage(nodeId, this)

        employManager?.popUID(nodeId,
                tvEmployNameD,
                tvEmpAgeD,
                tvEmpPhoneD,
                tvEmpYearD,
                tvEmpCollegeD,
                tvEmpDegreeD,
                tvEmpGradeD,
                tvIdD,
                this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.getId()) {
                R.id.btnAcceptCv -> dialog()

            }
        }
    }


    fun dialog() {

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Accept Application")
        //set message for alert dialog
        builder.setMessage("Are you shore to accept this cv")
//        builder.setIcon(androi)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            sharedKey = SharedPrefrenceHelper()
            userName = sharedKey.getUsername(mContext)
            try {
                companyAcceptOrderManager?.sendContactsBulk(userName,nodeId)
                ToastMessage.addMessage("cv accepted",mContext)
                var i = Intent(mContext, CompanyMain::class.java)
                startActivity(i)
                finish()
            } catch (e: Exception) {
            }
            // Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()
        }
//        //performing cancel action
//        builder.setNeutralButton("Cancel") { dialogInterface, which ->
//            Toast.makeText(applicationContext, "clicked cancel\n operation cancel", Toast.LENGTH_LONG).show()
//        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }


}
