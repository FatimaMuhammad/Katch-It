package com.example.katche_it.activities.activity

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class PuzzleAdapter(private val context: Context, private val pieces: MutableList<PuzzlePiece>) : BaseAdapter() {
    override fun getCount(): Int = pieces.size
    override fun getItem(position: Int): Any = pieces[position]
    override fun getItemId(position: Int): Long = pieces[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val piece = pieces[position]
        val imageView = ImageView(context)
        imageView.setImageResource(piece.resourceId)
        return imageView
    }
}