package com.example.katche_it.activities.models

data class BrandData(val brandName: String,
                     val ownerName: String,
                     val contactNumber: String,
                     val logoUrl: String,
                     var products: MutableMap<String,products> = mutableMapOf()
)
