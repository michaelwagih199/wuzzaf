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
import com.example.wuzzaf.activities.JobSuggestionDetails
import com.example.wuzzaf.entities.JopRequirements


class JobSugesstionAdapter(val context: Context, val models: ArrayList<JopRequirements?>) :
        RecyclerView.Adapter<JobSugesstionAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): JobSugesstionAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.job_suggest_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobSugesstionAdapter.ItemViewHolder, position: Int) {
        models[position]?.let {
            holder.bindItems(context, it)
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }//


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("NewApi")
        fun bindItems(context: Context, model: JopRequirements) {

            val tvJobTitle =
                    itemView.findViewById<TextView>(R.id.tvJobTitle) as TextView
            val tvJobDescription =
                    itemView.findViewById<TextView>(R.id.tvJobDescription) as TextView
            val tvCompanyName =
                    itemView.findViewById<TextView>(R.id.tvCompanyName) as TextView
            val edit_image_details = itemView.findViewById<ImageView>(R.id.edit_image_details) as ImageView

            tvJobTitle.text = model.jopTitle
            tvJobDescription.text = model.jopDescription
            tvCompanyName.text = model.id
            var id = model.documentId

            edit_image_details.setOnClickListener {
                var i = Intent(context, JobSuggestionDetails::class.java)
                i.putExtra("ID", model.id)
                context.startActivity(i)
                (context as Activity).finish()
            }

        }

    }


}