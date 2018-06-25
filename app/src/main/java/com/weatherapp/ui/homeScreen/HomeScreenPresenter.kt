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

package com.a2a.android.hbtf.ui.login


import com.a2a.android.hbtf.data.network.model.LocationObject
import com.a2a.android.hbtf.ui.base.BasePresenter
import com.weatherapp.ui.homeScreen.HomeScreenMvpPresenter
import com.weatherapp.ui.homeScreen.HomeScreenMvpView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeScreenPresenter<V : HomeScreenMvpView> :
        BasePresenter<V>(), HomeScreenMvpPresenter<V> {


    var arrayOfRegio: ArrayList<LocationObject>? = null
    var arrayOfCountry: ArrayList<LocationObject>? = null
    var arrayOfArea: ArrayList<LocationObject>? = null

    override fun getArea(countryCode: String) {
        if (arrayOfArea == null) {
            mvpView!!.showLoading()
            compositeDisposable.add(
                    dataAccManager.getArea(countryCode).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    { t ->
                                        mvpView!!.hideLoading()
                                        arrayOfArea = t
                                        mvpView!!.showArea(arrayOfArea!!)
                                    }
                            )
                            {
                                mvpView!!.hideLoading()
                            }
            )


        } else
            mvpView!!.showArea(arrayOfArea!!)
    }

    override fun getCountry(regionCode: String) {
        if (arrayOfCountry == null) {
            mvpView!!.showLoading()
            compositeDisposable.add(
                    dataAccManager.getCountry(regionCode).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    { t ->
                                        mvpView!!.hideLoading()
                                        arrayOfCountry = t
                                        mvpView!!.showCountry(arrayOfCountry!!)
                                    }
                            )
                            {
                                mvpView!!.hideLoading()
                            }
            )


        } else
            mvpView!!.showCountry(arrayOfCountry!!)
    }

    override fun getRegion() {
        if (arrayOfRegio == null) {
            mvpView!!.showLoading()
            compositeDisposable.add(
                    dataAccManager.getRegion().subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    { t ->
                                        mvpView!!.hideLoading()
                                        arrayOfRegio = t
                                        mvpView!!.showRegionList(arrayOfRegio!!)
                                    }
                            )
                            {
                                mvpView!!.hideLoading()

                            }

            )


        } else
            mvpView!!.showRegionList(arrayOfRegio!!)
    }

    override fun getLocationKey(areaKey: String, countryCode: String) {
        mvpView!!.showLoading()
        compositeDisposable.add(
                dataAccManager.getLocationCode(countryCode,areaKey).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(
                                { t ->
                                    if (t.size != 0) {
                                        mvpView!!.successGetLocationKey(t[0])
                                    } else
                                        mvpView!!.noTempFound()
                                    mvpView!!.hideLoading()

                                }
                        )
                        {
                            mvpView!!.hideLoading()
                        }
        )

    }

    override fun getTemp(locationKey: String) {
        mvpView!!.showLoading()
        compositeDisposable.add(
                dataAccManager.getTemp(locationKey).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(
                                { t ->
                                    if (t.size != 0) {
                                        mvpView!!.hideLoading()
                                        mvpView!!.showTemp(t[0])
                                    } else
                                        mvpView!!.noTempFound()
                                }
                        )
                        {
                            mvpView!!.hideLoading()
                        }
        )
    }


}
