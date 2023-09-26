package com.example.katche_it.activities.activity


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import com.example.katche_it.activities.adapters.BrandAdapter
import com.example.katche_it.activities.models.BrandData

import com.example.katche_it.databinding.ActivityShowBrandsBinding
import com.google.firebase.firestore.FirebaseFirestore


class ShowBrandsActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
   lateinit var binding: ActivityShowBrandsBinding
    private lateinit var adapter: BrandAdapter
    private var brandslist = mutableListOf<BrandData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBrandsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        populateDummyData()
        setUpFirestore()
    }

    private fun setUpFirestore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("BrandData  ")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(BrandData::class.java).toString())
           //
            brandslist.addAll(value.toObjects(BrandData::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun populateDummyData() {
        brandslist.add(BrandData("ijk","Ali","0986422","www.google.com"))
        brandslist.add(BrandData("fashionista","alina","0718942","www.google.com"))
        brandslist.add(BrandData("babafoods","tahir","0321132","www.google.com"))
        brandslist.add(BrandData("homedecore","Alia","0986422","www.google.com"))
        brandslist.add(BrandData("homechefs","sikanda","0986422","www.google.com"))
        brandslist.add(BrandData("homesalon","Aalima","0986422","www.google.com"))
        brandslist.add(BrandData("homemaid","farah","0986422","www.google.com"))

    }

    private fun setUpViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = BrandAdapter(this,brandslist)
        binding.brandRecyclerView.layoutManager = GridLayoutManager(this,2)
        binding.brandRecyclerView.adapter = adapter
    }
}