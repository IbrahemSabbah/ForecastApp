package com.weatherapp.data.model

data class TempObject(
        val DateTime: String,
        val EpochDateTime: Int,
        val WeatherIcon: Int,
        val IconPhrase: String,
        val IsDaylight: Boolean,
        val Temperature: Temperature,
        val PrecipitationProbability: Int,
        val MobileLink: String,
        val Link: String
)