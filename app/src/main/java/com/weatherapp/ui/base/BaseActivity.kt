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


import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import butterknife.Unbinder
import com.weatherapp.ui.base.MvpView
import com.weatherapp.util.CommonUtils
import android.support.design.widget.Snackbar
import com.blankj.utilcode.util.NetworkUtils
import com.weatherapp.R


/**
 * Created by janisharali on 27/01/17.
 */

abstract class BaseActivity : AppCompatActivity(), MvpView {

    private var mProgressDialog: ProgressDialog? = null
    private var mUnBinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }


    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        val textView = sbView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    override fun onError(message: String?) {
        if (message != null) {
            showMessage(message)
        } else {
            showMessage(getString(R.string.api_default_error))
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String) {

        showSnackBar(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }




    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    fun setUnBinder(unBinder: Unbinder) {
        mUnBinder = unBinder
    }

    override fun onDestroy() {
        if (mUnBinder != null) {
            mUnBinder!!.unbind()
        }
        super.onDestroy()
    }

    protected abstract fun setUp()


}
