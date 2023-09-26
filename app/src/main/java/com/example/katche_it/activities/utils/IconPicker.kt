package com.example.katche_it.activities.utils

import com.example.katche_it.R

object IconPicker  {
        private val icons = arrayOf(
            R.drawable.ic_icon1,
            R.drawable.ic_icon2,
            R.drawable.ic_icon3,
            R.drawable.ic_icon4,
            R.drawable.ic_icon5,

        )
        private var currentIcon = 0

        fun getIcon(): Int {
            currentIcon = (currentIcon + 1) % icons.size
            return icons[currentIcon]
        }
    }
