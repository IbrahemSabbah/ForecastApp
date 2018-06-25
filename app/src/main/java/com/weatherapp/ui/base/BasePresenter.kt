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

package com.a2a.android.hbtf.ui.base

/**
 * Created by janisharali on 27/01/17.
 */

import com.a2a.android.hbtf.data.ApiService
import com.a2a.android.hbtf.data.DataAccessManager
import com.a2a.android.hbtf.data.network.NetworkModule
import com.weatherapp.ui.base.MvpPresenter
import com.weatherapp.ui.base.MvpView
import io.reactivex.disposables.CompositeDisposable
import retrofit2.adapter.rxjava2.Result.response
import com.weatherapp.data.network.ErrorUtils
import com.weatherapp.data.network.APIError
import retrofit2.HttpException


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<V : MvpView> : MvpPresenter<V> {




    lateinit var compositeDisposable: CompositeDisposable
    lateinit var apiService: ApiService
    lateinit var dataAccManager: DataAccessManager

    var mvpView: V? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
        this.compositeDisposable = CompositeDisposable()
        this.apiService = NetworkModule.apiService
        this.dataAccManager = DataAccessManager()
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }


    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")


}
