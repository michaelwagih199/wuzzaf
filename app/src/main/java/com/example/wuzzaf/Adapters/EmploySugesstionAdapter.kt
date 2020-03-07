package com.example.wuzzaf.Adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wuzzaf.R
import com.example.wuzzaf.activities.EmploySuggetDetails
import com.example.wuzzaf.activities.JobSuggestionDetails
import com.example.wuzzaf.entities.EmployApplyOrder
import com.example.wuzzaf.entities.EmplyCv


class EmploySugesstionAdapter(val context: Context, val models: ArrayList<EmployApplyOrder?>) :
        RecyclerView.Adapter<EmploySugesstionAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EmploySugesstionAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.employ_suggest_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmploySugesstionAdapter.ItemViewHolder, position: Int) {
        models[position]?.let {
            holder.bindItems(context, it)
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }//


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("NewApi")
        fun bindItems(context: Context, model: EmployApplyOrder) {

            val tvEmployNameItem =
                    itemView.findViewById<TextView>(R.id.tvEmployNameItem) as TextView
//            val tvCollegeItem =
//                    itemView.findViewById<TextView>(R.id.tvCollegeItem) as TextView
//            val tvPhoneItem =
//                    itemView.findViewById<TextView>(R.id.tvPhoneItem) as TextView
            val edit_image_detailsEmploy = itemView.findViewById<ImageView>(R.id.edit_image_detailsEmploy) as ImageView

            tvEmployNameItem.text = model.userName

            var id = model.documentId

            edit_image_detailsEmploy.setOnClickListener {
                var i = Intent(context, EmploySuggetDetails::class.java)
                i.putExtra("ID", model.userName)
                context.startActivity(i)
                (context as Activity).finish()
            }

        }

    }


}