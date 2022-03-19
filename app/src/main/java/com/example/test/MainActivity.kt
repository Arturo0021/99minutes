package com.example.test

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.test.adapter.CustomAdapterList
import com.example.test.data.BDDInsert
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.LocationService
import com.example.test.utils.ManagerPermissions
import com.example.test.view.Layout
import com.example.test.viewmodel.LocationViewModel
import com.example.test.viewmodel.PlaceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val placeviewmodel: PlaceViewModel by viewModels()
    private lateinit var adapter : CustomAdapterList
    private lateinit var managePermissions: ManagerPermissions
    private val PERMISSIONREQUESCODE = 123
    private lateinit var intentService : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managePermissions = ManagerPermissions(this, returnPermission(), PERMISSIONREQUESCODE)
        managePermissions.checkPermissions()

        binding.maps.setOnClickListener {
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
        }

        binding.search.setOnClickListener {
            Toast.makeText(this@MainActivity, "Queda poco tiempo", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivityForResult(intent, 100)
        }

        initServiceLocation()
    }

    private fun initServiceLocation() {
        intentService = Intent(this, LocationService::class.java).also { intent ->
            startService(intent)
        }
    }

    fun returnPermission(): List<String> {
        val list = listOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET
        )
        return list
    }

    private fun callViewModel(query : String) {
        placeviewmodel.onCreate(query)
        placeviewmodel.placeModel.observe(this@MainActivity, Observer {
            binding.container.removeAllViews()

            for(PlaceModel in it.results) {
                BDDInsert(this@MainActivity).insertResults(PlaceModel, query)
            }

            adapter = CustomAdapterList(this@MainActivity, it.results)
            val recycler = Layout().recyclerViewCreator(this@MainActivity)
            recycler.layoutManager = Layout().layoutManager(this@MainActivity)
            recycler.adapter = adapter
            binding.container.addView(recycler)
        })

        placeviewmodel.isLoading.observe(this@MainActivity, Observer {
            binding.progress.isVisible = it
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null) {
            val d = data.getStringExtra("search")
            callViewModel(d!!)
        }
    }

    override fun onStop() {
        super.onStop()
        stopService(intentService)
        Log.v("#LOG", "OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        initServiceLocation()
        Log.v("#LOG", "OnRestart")
    }
}