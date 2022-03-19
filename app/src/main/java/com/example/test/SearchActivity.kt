package com.example.test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import com.example.test.adapter.CustomAdapterListSearch
import com.example.test.data.BDDInsert
import com.example.test.data.BDDSelect
import com.example.test.databinding.ActivitySearchBinding
import com.example.test.view.Layout

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    private lateinit var adapter : CustomAdapterListSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSearch( 1, "")

        binding.searchactivity.onActionViewExpanded()

        binding.searchactivity.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                callViewModel(query!!)
                finishedSearch(query)
                binding.searchactivity.onActionViewCollapsed()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                getSearch( 2, query!!)
                return false
            }

        })
    }

    private fun getSearch(case : Int, query : String){
        val result = when(case){
            1 -> {
                BDDSelect(this@SearchActivity).getSearch()
            } else -> {
                BDDSelect(this@SearchActivity).getSearchText(query)
            }
        }

        adapter = CustomAdapterListSearch(this@SearchActivity, result)

        adapter.onItemClick = {
            finishedSearch(it.busquedas)
        }

        val recycler = Layout().recyclerViewCreator(this@SearchActivity)
        recycler.layoutManager = Layout().layoutManager(this@SearchActivity)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.container.addView(recycler)
    }

    private fun callViewModel(query : String) {
        /*locationViewModel.onCreate()
        locationViewModel.locationModel.observe(this@MainActivity, Observer {
            Toast.makeText(this, "${it.longitudtext}---${it.longitudtext}", Toast.LENGTH_SHORT).show()
        })*/
        BDDInsert(this@SearchActivity).insertSearch(query)
    }

    fun finishedSearch(string: String) {
        val intent = Intent()
        intent.putExtra("search", string)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}