package com.example.wuzzaf.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wuzzaf.Adapters.JobSugesstionAdapter
import com.example.wuzzaf.R
import com.example.wuzzaf.dao.JopRequirementsManager
import com.example.wuzzaf.helpers.SharedPrefrenceHelper
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class JopSugisition : AppCompatActivity() {

    private lateinit var mContext: Context
    lateinit var recycleCustomer: RecyclerView
    private lateinit var jopRequirementsManager: JopRequirementsManager
    private lateinit var adapter: JobSugesstionAdapter
    var profitArray = ArrayList<JobSugesstionAdapter?>()
    private val firebaseFirestore: FirebaseFirestore? = null
    private val contactsCollectionReference: CollectionReference? = null
    private lateinit var userName:String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jop_sugisition)
        mContext = this
        recycleCustomer = findViewById(R.id.recycleJobsSuggested)
        recycleCustomer.setLayoutManager(LinearLayoutManager(this))
        jopRequirementsManager = JopRequirementsManager.newInstance()
        //userName = sharedPrefrenceHelper.getUsername(this)

        getSuggestList()

    }

    private fun getSuggestList() {

        jopRequirementsManager.getData2(mContext,recycleCustomer)

    }


}


