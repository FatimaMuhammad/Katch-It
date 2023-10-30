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
                productList.add(products("Baba Foods", "10%", "9-dec-23", R.drawable.chapati))
                productList.add(products("Fashionista", "10%", "9-dec-23", R.drawable.meipedi))
                productList.add(products("Fashionista", "10%", "9-dec-23", R.drawable.mehandi))
                productList.add(products("Home Maid", "10%", "9-dec-23", R.drawable.swaeeping))
                productList.add(products("Home Decor", "10%", "9-dec-23",R.drawable.bedshet))
                productList.add(products("Baba Foods", "10%", "9-dec-23", R.drawable.chat))
                productList.add(products("Home Maid", "10%", "9-dec-23", R.drawable.carpet))
                productList.add(products("Home Chefs", "10%", "9-dec-23", R.drawable.feshveg))
                productList.add(products("Home Chefs", "10%", "9-dec-23", R.drawable.veg))
                productList.add(products("Baba Foods", "10%", "9-dec-23", R.drawable.parathy))
                productList.add(products("Home Salon", "10%", "9-dec-23", R.drawable.haircolor))
                productList.add(products("Baba Foods", "10%", "9-dec-23", R.drawable.aaloparatha))
                productList.add(products("Home Decor", "10%", "9-dec-23",R.drawable.bed))
                productList.add(products("Baba Foods", "10%", "9-dec-23",R.drawable.amlet))
                productList.add(products("Baba Foods", "10%", "9-dec-23",R.drawable.choly))
                productList.add(products("Baba Foods", "10%", "9-dec-23",R.drawable.doodhpati))
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
