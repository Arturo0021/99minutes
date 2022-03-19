package com.example.test.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.DetailsActivity
import com.example.test.databinding.AdapterlistBinding
import com.example.test.model.PlaceModelResults

class CustomAdapterList(val context : Context, val items : List<PlaceModelResults>) : RecyclerView.Adapter<CustomAdapterList.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = AdapterlistBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(items[position])

    inner class ViewHolder(val binding: AdapterlistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaceModelResults) {
            Glide.with(context).load(item.icon).into(binding.iclogo)
            binding.name.text     = item.name
            binding.vicinity.text = item.vicinity
            binding.placeid.text  = item.place_id
            binding.card.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("placeid", item.place_id)
                context.startActivity(intent)
            }
        }
    }

}
