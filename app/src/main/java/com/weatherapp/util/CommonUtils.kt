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

package com.weatherapp.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import com.blankj.utilcode.util.DeviceUtils
import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier.TRANSIENT
import android.R.id.edit
import java.util.UUID.randomUUID
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.provider.Settings
import com.weatherapp.AppController
import com.weatherapp.R
import java.util.*


/**
 * Created by janisharali on 27/01/17.
 */

object CommonUtils {


    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }


    fun getDeviceId(): String {
        return getId()
    }

    fun getDeviceInfo(): String {
        return DeviceUtils.getManufacturer() + "_" + DeviceUtils.getModel() + "_" + DeviceUtils.getSDKVersionCode()
    }





    @Synchronized
    fun getUUID(): String {
        var uniqueID: String? = null
        val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"
        if (uniqueID == null) {
            val sharedPrefs = AppController.instance.applicationContext.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE)
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null)
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString()
                val editor = sharedPrefs.edit()
                editor.putString(PREF_UNIQUE_ID, uniqueID)
                editor.commit()
            }
        }
        return uniqueID
    }

    fun getId(): String {
        return Settings.Secure.getString(AppController.instance.getContentResolver(),
                Settings.Secure.ANDROID_ID)
    }

    fun getOSVersion(): String {
        return DeviceUtils.getSDKVersionCode().toString()
    }
    fun getLocaleString(stringAr: String = "", stringEn: String = ""): String {
        if (Locale.getDefault().displayLanguage == "ar")
            return stringAr
        return stringEn

    }

    fun convertClassToJson(`object`: Any): String {
        val gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
                .create()
        return gson.toJson(`object`)

    }

}// This utility class is not publicly instantiable


