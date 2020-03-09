package com.example.wuzzaf.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.EmployFirestoreManager
import com.example.wuzzaf.dao.JopRequirementsManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.example.wuzzaf.helpers.ToastMessage

class JopSugisition : AppCompatActivity(), View.OnClickListener {

    private lateinit var mContext: Context
    lateinit var recycleCustomer: RecyclerView
    private lateinit var jopRequirementsManager: JopRequirementsManager
    private lateinit var employCv: EmployFirestoreManager
    private lateinit var userName: String
    lateinit var sharedKey: SharedPrefrenceHelper
    lateinit var tvCollegeCv: TextView
    lateinit var tvExperienceYearsCv: TextView
    lateinit var tvGradeCv: TextView
    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jop_sugisition)
        mContext = this
        recycleCustomer = findViewById(R.id.recycleJobsSuggested)
        recycleCustomer.setLayoutManager(LinearLayoutManager(this))
        jopRequirementsManager = JopRequirementsManager.newInstance()
        employCv = EmployFirestoreManager.newInstance()
        tvCollegeCv = findViewById(R.id.tvCollegeCv)
        tvExperienceYearsCv = findViewById(R.id.tvExperienceYearsCv)
        img = findViewById(R.id.imageViewSeeSuggest)
        img.setOnClickListener(this)
        tvGradeCv = findViewById(R.id.tvGradeCv)
        sharedKey = SharedPrefrenceHelper()
        userName = sharedKey.getUsername(mContext)
        pupUi()
        //getSuggestList()

    }

    private fun pupUi() {

        employCv.popUISuggest(userName, tvExperienceYearsCv, tvCollegeCv, tvGradeCv, this)

    }

    private fun getSuggestList() {

       // ToastMessage.addMessage(userName, this)
        jopRequirementsManager.getData(mContext,
                recycleCustomer,
                tvCollegeCv.text.toString(),
                tvExperienceYearsCv.text.toString(),
                tvGradeCv.text.toString())

    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.imageViewSeeSuggest -> getSuggestList()
            }
        }

    }


}


