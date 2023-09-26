package com.example.katche_it.activities.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.katche_it.R
import com.example.katche_it.activities.adapters.ProductAdapter
import com.example.katche_it.activities.models.products
import com.example.katche_it.databinding.ActivityDisplayProductsBinding


class DisplayProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayProductsBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val productList = createDummyProductList() // Replace with your actual list of products

                val productRecyclerView = findViewById<RecyclerView>(R.id.productRecyclerView)
                productRecyclerView.layoutManager = LinearLayoutManager(this)
                productAdapter = ProductAdapter(this, productList)
                productRecyclerView.adapter = productAdapter
            }

            private fun createDummyProductList(): List<products> {
                val productList = mutableListOf<products>()

                // Add dummy product data
                productList.add(products("cookies", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("meniPedis", "10%", "9-dec-23", "image_url_2"))
                productList.add(products("pizza", "10%", "9-dec-23", "image_url_3"))
                productList.add(products("homeServices", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("chickenPasta", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("frozenKabab", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("readyMadeCushions", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("Pillos", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("homeMadeSluxh", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("cookedFries", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("vegetableKari", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("cookies", "10%", "9-dec-23", "image_url_1"))
                productList.add(products("frozen parathy", "10%", "9-dec-23", "image_url_1"))
                return productList
            }
        }


/*recyclerView = binding.productRecyclerView
recyclerView.layoutManager = LinearLayoutManager(this)
productAdapter = ProductAdapter(emptyList(products))
recyclerView.adapter = productAdapter

val db = FirebaseFirestore.getInstance()

// Fetch products from Firestore
db.collection("products")
    .get()
    .addOnSuccessListener { result ->
        val productsList = mutableListOf<products>()
        for (document in result) {
            val productName = document.getString("productName") ?: ""
            val discountDetails = document.getString("discountDetails") ?: ""
            val validity = document.getString("validity") ?: ""
            val imageUrl = document.getString("imageUrl") ?: ""
            val products = products(productName, discountDetails, validity, imageUrl)
            productsList.add(products)
        }
        productAdapter = ProductAdapter(productsList)
        recyclerView.adapter = productAdapter
    }
    .addOnFailureListener { e ->
        Toast.makeText(
            this,
            "unable to show discounted Products : ${e.message}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
}*/
