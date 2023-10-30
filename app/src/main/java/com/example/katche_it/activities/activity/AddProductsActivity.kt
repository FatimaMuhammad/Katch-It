package com.example.katche_it.activities.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.activities.models.products
import com.example.katche_it.databinding.ActivityAddProductsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductsBinding
    private lateinit var productNameEditText: EditText
    private lateinit var discountDetailsEditText: EditText
    private lateinit var validityEditText: EditText
    private lateinit var uploadButton: Button
    private lateinit var savedButton: Button
    private lateinit var imageView: ImageView

    private val GALLERY_REQUEST_CODE = 1001
    private lateinit var selectedImageUri: Uri
    private lateinit var storageReference: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UI elements
        productNameEditText = binding.productNameEditText
        discountDetailsEditText = binding.discountDetailsEditText
        validityEditText = binding.validityEditText
        uploadButton = binding.uploadButton
        savedButton = binding.SavedButton
        imageView = binding.imageView
        storageReference = FirebaseStorage.getInstance().reference.child("product_images")

        // Set an OnClickListener for the "Upload" button
        uploadButton.setOnClickListener {
            // Get product information from EditText fields
            val productName = productNameEditText.text.toString()
            val discountDetails = discountDetailsEditText.text.toString()
            val validity = validityEditText.text.toString()

            // Upload product data to Firestore
            val db = FirebaseFirestore.getInstance()
            val productsCollection = db.collection("products")
            val products = products(productName, discountDetails, validity, imageResourceId = 100)

            productsCollection.add(products)
                .addOnSuccessListener {
                    // Brand registration in Firestore successful
                    // Toast.makeText(this, "Products Add successfully", Toast.LENGTH_SHORT)
                        //.show()
                    //clearEditTextFields()
                    val intent = Intent(this, DisplayProductsActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Upload failed: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }

        // Set an OnClickListener for the ImageView to open the gallery picker
        imageView.setOnClickListener {
            openGallery()
        }

        // Set an OnClickListener for the "Saved" button to upload the selected image
        savedButton.setOnClickListener {
            if (::selectedImageUri.isInitialized) {
                uploadImage(selectedImageUri)
            }
            binding.SavedButton.setOnClickListener {
                Toast.makeText(this, "Data received. Authenticating your account.\n Once verified, your products will be showcased in our app", Toast.LENGTH_SHORT)
                    .show()
                clearEditTextFields()
                val intent = Intent(this, DisplayProductsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data!!

            // Handle the selected image URI (e.g., display it in an ImageView)
            imageView.setImageURI(selectedImageUri)
        }
    }

    private fun uploadImage(imageUri: Uri) {
        val imageFileName = "${System.currentTimeMillis()}.jpg"
        val imageRef = storageReference.child(imageFileName)
        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Image uploaded successfully, get the download URL
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Handle the download URL (e.g., save it to Firestore along with other product data)
                    val downloadUrl = uri.toString()
                    val productName = productNameEditText.text.toString()
                    val discountDetails = discountDetailsEditText.text.toString()
                    val validity = validityEditText.text.toString()

                    // Upload product data including the image URL to Firestore
                    val db = FirebaseFirestore.getInstance()
                    val productsCollection = db.collection("products")
                    val products = products(productName, discountDetails, validity, imageResourceId = 100 )
                    productsCollection.add(products)
                        .addOnSuccessListener {
                            // Brand registration in Firestore successful
                            Toast.makeText(this, "Data received. Authenticating" +
                                    " your \n account.Once verified, your products " +
                                    "\n will be showcased in our app", Toast.LENGTH_SHORT)
                                .show()
                            clearEditTextFields()
                            val intent = Intent(this, DisplayProductsActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener {
                                Toast.makeText(this, "Upload failed", Toast.LENGTH_SHORT).show()
                        }

                }
            } else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun clearEditTextFields() {
        productNameEditText.text.clear()
        discountDetailsEditText.text.clear()
        validityEditText.text.clear()
    }
}
