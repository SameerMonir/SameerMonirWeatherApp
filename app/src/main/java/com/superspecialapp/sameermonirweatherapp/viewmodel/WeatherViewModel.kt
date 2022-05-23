package com.superspecialapp.sameermonirweatherapp.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superspecialapp.sameermonirweatherapp.model.WeatherResponse
import com.superspecialapp.sameermonirweatherapp.fragment.WeatherRepoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val repoImpl: WeatherRepoImpl): ViewModel() {
    private val _forecast = MutableLiveData<WeatherResponse>()
    val forecast: LiveData<WeatherResponse> get() = _forecast

    fun getForecast(city: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repoImpl.getForecast(city)
            _forecast.postValue(response)
        }
    }
}
