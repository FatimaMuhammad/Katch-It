package com.example.katche_it.activities.utils

object ColorPicker {
        private val colors = arrayOf(
            "#8B0000",
            "#FF4500",
            "#556B2F",
            "#228B22",
            "#800080",
            "#191970",
            "#FED93E",
            "#800080",
            "#800020",
            "#D2691E",
            "#000080",
            "#800000",
            "#708090",
            "#3E583F",
            "#964B00"

        )
        private var currentColorIndex = 0

        fun getColor(): String {
            currentColorIndex = (currentColorIndex + 1) % colors.size
            return colors[currentColorIndex]
        }
    }