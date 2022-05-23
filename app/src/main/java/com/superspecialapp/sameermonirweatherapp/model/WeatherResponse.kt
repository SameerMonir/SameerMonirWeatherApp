package com.superspecialapp.sameermonirweatherapp.model

import android.os.Parcelable
import com.superspecialapp.sameermonirweatherapp.MainActivity
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*
import kotlin.math.roundToInt

data class WeatherResponse(
    val list: List<WeatherListItem>
)

@Parcelize
data class WeatherListItem(
    val weather: @RawValue List<Weather>,
    val main: Main,
    val name: String
): Parcelable {

    fun getCity(): String {
        return this.name
    }

    fun getDescription(): String {
        return this.weather[0].main.capitalizeWords()
    }

    fun getDetailedDescription(): String {
        return this.weather[0].description.capitalizeWords()
    }

    fun getFullTemp(): String {

        println( "**** model " + MainActivity.tempButton?.text)

        return "Temp: " + convertTemp(this.main.temp)
    }

    fun getTemp(): String {
        return convertTemp(this.main.temp)
    }

    fun getFeelsLike(): String {
        return "Feels like: " + convertTemp(this.main.feels_like)
    }

    private fun convertTemp(tempInK: Double) : String {
        val tempScale = MainActivity.tempButton?.text

        var current_temp = when (tempScale) {
            "°F" -> ((tempInK * 1.8) - 459.67)
            "°C" -> (tempInK - 273.15)
            else -> tempInK
        }

        return (current_temp.roundToInt()).toString() + tempScale
    }

    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it ->
        it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }
}

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Parcelize
data class Main(
    val temp: Double,
    val feels_like: Double
): Parcelable