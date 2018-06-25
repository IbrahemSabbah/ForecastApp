/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.a2a.android.hbtf.data

import com.a2a.android.hbtf.data.network.NetworkModule
import com.a2a.android.hbtf.data.network.model.LocationObject
import com.weatherapp.data.model.LocationKey
import com.weatherapp.data.model.TempObject
import io.reactivex.Observable


/**
 * Created by janisharali on 27/01/17.
 */

open class DataAccessManager : ApiAccessManager {

    override fun getTemp(locationKey: String): Observable<ArrayList<TempObject>> {
        return apiService.getTemp(locationKey)
    }

    override fun getLocationCode(areaCode: String, countryCode: String): Observable<ArrayList<LocationKey>> {
        return apiService.getLocationKey(areaCode,countryCode)

    }

    override fun getArea(countryCode: String): Observable<ArrayList<LocationObject>> {
        return apiService.getArea(countryCode)
    }

    override fun getCountry(regionCode: String): Observable<ArrayList<LocationObject>> {
        return apiService.getCountry(regionCode)
    }

    override fun getRegion(): Observable<ArrayList<LocationObject>> {
        return apiService.getRegion()
    }

    var apiService = NetworkModule.apiService


}
