package com.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.a2a.android.hbtf.data.network.model.LocationObject
import com.weatherapp.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_item.view.*

class LocationAdapter(private val myDataset: ArrayList<LocationObject>,val clickListener: (LocationObject) -> Unit) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val textView = view.textView

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): LocationAdapter.ViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val locationObject=myDataset[position]
        holder.textView.text =locationObject.EnglishName
        holder?.textView?.setOnClickListener { clickListener(locationObject) }

    }

    override fun getItemCount() = myDataset.size
}
