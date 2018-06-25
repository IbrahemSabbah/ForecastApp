package com.weatherapp.data.network

import com.google.gson.annotations.Expose


data class APIError (var statusCode:Int=0,var message:String="" )