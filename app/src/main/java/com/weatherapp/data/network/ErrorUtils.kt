package com.weatherapp.data.network

import com.a2a.android.hbtf.data.network.NetworkModule
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException


object ErrorUtils {

    fun parsError(response: Response<*>): APIError {
        val converter: Converter<ResponseBody, APIError>
        converter = NetworkModule.retrofit.responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))
        val error: APIError

        try {
            error = converter.convert(response.errorBody())
        } catch (e: IOException) {
            return APIError()
        }

        return error
    }
}
