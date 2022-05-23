package com.superspecialapp.sameermonirweatherapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.superspecialapp.sameermonirweatherapp.databinding.ForecastListBinding
import com.superspecialapp.sameermonirweatherapp.model.WeatherListItem

class WeatherRecyclerAdapter(private val forecast: MutableList<WeatherListItem> = mutableListOf(),
                             private val openDetails: (WeatherListItem) -> Unit
): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setForecast(newList: List<WeatherListItem>) {
        forecast.clear()
        forecast.addAll(newList)
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val binding: ForecastListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastItem: WeatherListItem) {
            binding.tvDescription.text = forecastItem.getDescription()
            binding.tvTemperature.text = forecastItem.getFullTemp()

            binding.root.setOnClickListener {
                openDetails(forecastItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ForecastListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}