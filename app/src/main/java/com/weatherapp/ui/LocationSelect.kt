package com.weatherapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.a2a.android.hbtf.data.network.model.LocationObject
import com.a2a.android.hbtf.ui.base.BaseActivity
import com.weatherapp.R
import com.weatherapp.R.id.list
import com.weatherapp.ui.adapter.LocationAdapter

import kotlinx.android.synthetic.main.activity_location_select.*

class LocationSelect : BaseActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_select)

        setSupportActionBar(toolbar)

        viewManager = LinearLayoutManager(this)

        setUp()

    }

    override fun setUp() {
        var locationArray = intent.getParcelableArrayListExtra<LocationObject>("list")
        var type = TypeObject.valueOf(intent.getStringExtra("type"))
        viewAdapter = LocationAdapter(locationArray){
            val intent=Intent()
            intent.putExtra("object",it)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

        recyclerView = findViewById<RecyclerView>(R.id.recycleView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

        when(type){
            TypeObject.Region->setTitle(R.string.select_region)
            TypeObject.Country->setTitle(R.string.select_country)
            TypeObject.Area->setTitle(R.string.select_area)
        }

    }


    enum class TypeObject(i: String) {
        Region("0"), Country("1"), Area("2")
    }



}
