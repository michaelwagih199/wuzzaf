package com.example.wuzzaf.activities

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.EmployApplyOrderManager
import com.example.wuzzaf.dao.EmployFirestoreManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.example.wuzzaf.helpers.ToastMessage

class CompanyResult : AppCompatActivity() {

    private lateinit var mContext: Context
    lateinit var recycleCustomer: RecyclerView
    private lateinit var employFirestoreManager: EmployFirestoreManager
    private lateinit var emplyOrder: EmployApplyOrderManager
    var userName: String? = "m"
    var nodeId: String? = "m"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_result)

        mContext = this
        recycleCustomer = findViewById(R.id.recycleStaffSuggested)
        recycleCustomer.setLayoutManager(LinearLayoutManager(this))

        employFirestoreManager = EmployFirestoreManager.newInstance()
        emplyOrder = EmployApplyOrderManager.newInstance()

        val key = SharedPrefrenceHelper()
        userName = key.getUsername(mContext)

        getSuggestList()

    }

    private fun getSuggestList() {
        emplyOrder.getData2(mContext, recycleCustomer, userName)

    }

}