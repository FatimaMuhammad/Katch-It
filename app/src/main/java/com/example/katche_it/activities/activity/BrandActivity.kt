package com.example.katche_it.activities.activity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.R
import com.example.katche_it.activities.models.BrandData
import com.example.katche_it.databinding.ActivityBrandBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

@Suppress("DEPRECATION")
class BrandActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrandBinding
    private lateinit var storageReference: FirebaseStorage
    private lateinit var firestore: FirebaseFirestore
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("BrandData")

        binding.logoImageView.setOnClickListener { pickImage() }

        binding.checkBoxTerms
        val registerButton: Button = binding.registerButton
        val nextButton: Button = binding.Gotouplaod
        val viewButton: Button = binding.showBrands

        registerButton.setOnClickListener { registerBrand(logoUrl = String()) }

        // Open AddProductsActivity when the "Next" button is clicked
        nextButton.setOnClickListener {
            val intent = Intent(this, AddProductsActivity::class.java)
            startActivity(intent)
        }

        // Open ShowBrandsActivity when the "View" button is clicked
        viewButton.setOnClickListener {
            val intent = Intent(this, ShowBrandsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_REQUEST && resultCode == RESULT_OK) {
            val selectedImage: Uri? = data?.data
            if (selectedImage != null) {
                try {
                    val bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                    uploadLogoToFirebaseStorage(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun uploadLogoToFirebaseStorage(bitmap: Bitmap) {
        val logoRef = storageReference.reference.child("logos/${UUID.randomUUID()}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        logoRef.putBytes(data)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    val brandLogoUrl = uri.toString()
                    registerBrand(brandLogoUrl)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Logo upload failed: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun registerBrand(logoUrl: String) {
        val brandName = binding.brandNameEditText.text.toString()
        val ownerName = binding.ownerNameEditText.text.toString()
        val contactNumber = binding.contactNumberEditText.text.toString()
        if (brandName.isNotEmpty() && ownerName.isNotEmpty() && contactNumber.isNotEmpty()) {
            val brandData = BrandData(brandName, ownerName, contactNumber, logoUrl)
            // Store brand information in Firebase Realtime Database
            val brandId = dbRef.push().key // Generate a unique key
            brandId?.let {
                dbRef.child(brandId).setValue(brandData)
                    .addOnSuccessListener {
                        // Brand registration in Realtime Database successful
                        // Now, store brand information in Firestore
                        firestore.collection("BrandData")
                            .document(brandId)
                            .set(brandData)
                            .addOnSuccessListener {
                                // Brand registration in Firestore successful
                                Toast.makeText(this, "Brand data received." +
                                        " upload your products now. \n Once verified, your " +
                                        "brand and products will be showcased in our app.", Toast.LENGTH_SHORT)
                                    .show()
                                clearForm()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Firestore registration failed: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Realtime Database registration failed: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        } else {
            Toast.makeText(this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearForm() {
        binding.brandNameEditText.text.clear()
        binding.ownerNameEditText.text.clear()
        binding.contactNumberEditText.text.clear()
        binding.logoImageView.setImageResource(R.drawable.baseline_image_24)
    }

    companion object {
        private const val IMAGE_PICK_REQUEST = 1
    }
}
