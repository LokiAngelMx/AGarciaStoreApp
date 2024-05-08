package com.example.storeapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.data.ProductService
import com.example.storeapp.domain.adapters.ProductsAdapter
import com.example.storeapp.domain.models.Product
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var productList = emptyList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        getProducts()
    }

    // KISS keep it simple, stupid
    private fun getProducts() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)

        lifecycleScope.launch {
            val response = retrofitBuilder.getProducts()
            Log.i("ProductResponse", response.toString())
            productList = response
            recyclerView.adapter = ProductsAdapter(productList)
        }
    }
}