package com.example.katche_it.activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.katche_it.R
import com.example.katche_it.activities.activity.DisplayProductsActivity
import com.example.katche_it.activities.activity.QuestionActivity
import com.example.katche_it.activities.models.BrandData

class BrandAdapter(val context: Context, private val BrandData: List<BrandData>) :
    RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.BrandViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false)
        return BrandViewHolder(view)
    }

    override fun getItemCount(): Int {
        return BrandData.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {

        holder.brandNameTextView.text = BrandData[position].brandName
        holder.brandOwnerTextView.text = BrandData[position].ownerName
        holder.contactNumberTextView.text=BrandData[position].contactNumber
        holder.itemView.setOnClickListener {
            Toast.makeText(context, BrandData[position].brandName, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DisplayProductsActivity ::class.java)
            //intent.putExtra("DATE", quizzes[position].title)
            //context.startActivity(intent)
            intent.putExtra("BRAND_NAME", BrandData[position].brandName)
            intent.putExtra("OWNER_NAME", BrandData[position].ownerName)
            intent.putExtra("CONTACT_NUMBER", BrandData[position].contactNumber)

            context.startActivity(intent)
        }

    }

    inner class BrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
        var brandNameTextView: TextView = itemView.findViewById(R.id.brandNameTextView)
        var brandOwnerTextView: TextView = itemView.findViewById(R.id.brandOwnerTextView)
        var contactNumberTextView: TextView = itemView.findViewById(R.id.contactNumberTextView)
    }
}

