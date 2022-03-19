package com.example.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.test.databinding.AdapterlistsearchBinding
import com.example.test.model.LocationModel

class CustomAdapterListSearch(val context : Context, val items : List<LocationModel>) : RecyclerView.Adapter<CustomAdapterListSearch.ViewHolder>() {

    var onItemClick : ((LocationModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = AdapterlistsearchBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(items[position])

    inner class ViewHolder(val binding: AdapterlistsearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LocationModel) {
            binding.namesearch.text     = item.busquedas
        }
        init {
            binding.card.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }

}
