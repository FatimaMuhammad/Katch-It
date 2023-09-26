package com.example.katche_it.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.katche_it.R
import com.example.katche_it.activities.models.Question

class OptionAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       var optionView: TextView = itemView.findViewById<TextView>(R.id.quiz_option)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return  OptionViewHolder(view)
    }
    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener {
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == options[position]){
            Toast.makeText(context,options[position],Toast.LENGTH_SHORT).show()
            holder.itemView.setBackgroundResource(R.drawable.item_click_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_itembg)
        }

    }
    override fun getItemCount(): Int {
        return options.size
    }
}


