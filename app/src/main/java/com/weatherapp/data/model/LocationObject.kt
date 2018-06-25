package com.a2a.android.hbtf.data.network.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.weatherapp.util.CommonUtils
import kotlinx.android.parcel.Parcelize

/**
 * Created by appleuser on 2/6/18.
 */
@Parcelize
data class LocationObject(@Expose var ID: String = "", @Expose var LocalizedName: String = "", @Expose var EnglishName: String = ""):Parcelable