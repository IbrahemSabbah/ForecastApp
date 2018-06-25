package com.a2a.android.hbtf.data

import com.a2a.android.hbtf.data.network.model.LocationObject
import com.weatherapp.BuildConfig
import com.weatherapp.data.model.LocationKey
import com.weatherapp.data.model.TempObject
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @GET("/locations/v1/regions?apikey=" + BuildConfig.API_KEY)
    fun getRegion(): Observable<ArrayList<LocationObject>>

    @GET("/locations/v1/countries/{regionCode}?apikey=" + BuildConfig.API_KEY)
    fun getCountry(@Path("regionCode") regionCode: String): Observable<ArrayList<LocationObject>>

    @GET("/locations/v1/adminareas/{countryCode}?apikey=" + BuildConfig.API_KEY)
    fun getArea(@Path("countryCode") countryCode: String): Observable<ArrayList<LocationObject>>

    @GET("/locations/v1/cities/{countryCode}/search?apikey=" + BuildConfig.API_KEY)
    fun getLocationKey( @Path("countryCode") countryCode:String,@Query("q") areaCode: String): Observable<ArrayList<LocationKey>>

    @GET("/forecasts/v1/hourly/1hour/{locationKey}?apikey=" + BuildConfig.API_KEY)
    fun getTemp(@Path("locationKey") locationKey: String): Observable<ArrayList<TempObject>>


}