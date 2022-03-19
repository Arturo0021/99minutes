package com.example.test.view

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Layout {
    fun recyclerViewCreator(context: Context): RecyclerView {
        val recycler = RecyclerView(context)
        recycler.isNestedScrollingEnabled = true
        return recycler
    }

    fun layoutManager(context: Context) : LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        return linearLayoutManager
    }

}