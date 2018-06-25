package com.a2a.android.hbtf.data.network.model

import com.blankj.utilcode.util.DeviceUtils
import com.google.gson.annotations.Expose
import com.weatherapp.BuildConfig
import com.weatherapp.util.CommonUtils

/**
 * Created by appleuser on 2/20/18.
 */

open class BaseRequest {
    @Expose
    var DeviceID: String = ""
    @Expose
    var DeviceUUID: String = ""
    @Expose
    var Device: String = "Android"

    @Expose
    var Version: String = BuildConfig.VERSION_NAME
    @Expose
    var DeviceInfo: String = ""
    @Expose
    var OSVersion: String = ""
    @Expose
    var MAC: String = ""
    @Expose
    var apikey: String = ""




    init {
        DeviceID = CommonUtils.getDeviceId()
        DeviceUUID = CommonUtils.getUUID()
        DeviceInfo = CommonUtils.getDeviceInfo()
        OSVersion = CommonUtils.getOSVersion()
        apikey=BuildConfig.API_KEY

    }

}