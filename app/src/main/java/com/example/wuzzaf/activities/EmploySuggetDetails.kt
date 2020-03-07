package com.example.wuzzaf.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.wuzzaf.R
import com.example.wuzzaf.helpers.ToastMessage

class EmploySuggetDetails : AppCompatActivity() , View.OnClickListener{
    var nodeId: String? = "node"
    private lateinit var txvName: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emplpoy_sugget_details)

        val bundle: Bundle? = intent.extras
        if (bundle != null)
            nodeId = bundle.getString("ID")
        ToastMessage.addMessage(nodeId, this)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}
