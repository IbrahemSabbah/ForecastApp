package com.a2a.android.hbtf.data

import com.a2a.android.hbtf.data.network.model.LocationObject
import com.weatherapp.data.model.LocationKey
import com.weatherapp.data.model.TempObject
import io.reactivex.Observable
import java.util.*
import kotlin.collections.ArrayList

interface ApiAccessManager {

    fun getRegion(): Observable<ArrayList<LocationObject>>
    fun getCountry(regionCode: String): Observable<ArrayList<LocationObject>>
    fun getArea(countryCode: String): Observable<ArrayList<LocationObject>>
    fun getLocationCode(areaCode: String,countryCode: String): Observable<ArrayList<LocationKey>>
    fun getTemp(locationKey: String): Observable<ArrayList<TempObject>>


}