package com.example.katche_it.activities.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.katche_it.R
import com.example.katche_it.activities.activity.DisplayProductsActivity
import com.example.katche_it.activities.models.products



import com.bumptech.glide.Glide
import com.example.katche_it.activities.activity.MainActivity


class ProductAdapter(private val context: DisplayProductsActivity, private val productList: List<products>) :
RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Load image from the product's imageUrl using Glide
        Glide.with(context)
            .load(product.imageUrl)
            .placeholder(R.drawable.baseline_image_24)
            .into(holder.productImageView)

        holder.productNameTextView.text = product.productName
        holder.discountDetailsTextView.text = product.discountDetails
        holder.validityTextView.text = product.validity

        holder.itemView.setOnClickListener {
            Toast.makeText(context, product.productName, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            // Add any extra data you want to pass to the activity
            // intent.putExtra("KEY", value)
            context.startActivity(intent)
        }
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        var productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        var discountDetailsTextView: TextView = itemView.findViewById(R.id.discountDetailsTextView)
        var validityTextView: TextView = itemView.findViewById(R.id.validityTextView)
    }
}