package com.superspecialapp.sameermonirweatherapp.fragment

import com.superspecialapp.sameermonirweatherapp.api.ApiService
import com.superspecialapp.sameermonirweatherapp.model.WeatherResponse

interface WeatherRepo {
    // update to take in city as variable
    suspend fun getForecast(city: String?) : WeatherResponse
}

class WeatherRepoImpl (private val service: ApiService = ApiService.getApiService(),
                       private val city : String? = null
): WeatherRepo{

    override suspend fun getForecast(city: String?): WeatherResponse {
        val response = service.getForecast(name = city)

        if (response.isSuccessful) {
            return response.body()!!
        }

        return WeatherResponse(emptyList())
    }
}