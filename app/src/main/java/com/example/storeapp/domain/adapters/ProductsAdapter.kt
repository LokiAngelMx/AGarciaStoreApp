package com.example.storeapp.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.domain.models.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val products: List<Product>,
    val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.titleTV.text = product.title
        holder.price.text = product.computedPrice
        holder.ratingTV.text = product.rating.rate.toString()
        Picasso.get().load(product.image).resize(600,200).centerInside().into(holder.imageIV)
        holder.itemView.setOnClickListener {
            onProductClick(product)
        }
    }
}

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val categoryTV : TextView
    val price : TextView
    val imageIV : ImageView
    val titleTV : TextView
    val ratingTV : TextView

    init {
        categoryTV = view.findViewById(R.id.productCategory)
        price = view.findViewById(R.id.productPrice)
        imageIV = view.findViewById(R.id.productImage)
        titleTV = view.findViewById(R.id.productName)
        ratingTV = view.findViewById(R.id.productRating)
    }
}