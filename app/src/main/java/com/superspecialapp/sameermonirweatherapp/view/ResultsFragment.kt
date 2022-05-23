package com.superspecialapp.sameermonirweatherapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.superspecialapp.sameermonirweatherapp.MainActivity
//import com.superspecialapp.sameermonirweatherapp.R
import com.superspecialapp.sameermonirweatherapp.databinding.FragmentScrollBinding
import com.superspecialapp.sameermonirweatherapp.model.WeatherListItem
import com.superspecialapp.sameermonirweatherapp.fragment.WeatherRepoImpl
import com.superspecialapp.sameermonirweatherapp.viewmodel.WeatherViewModel
import java.util.*

class ResultsFragment(private var city: String?) : Fragment() {
    private var _binding: FragmentScrollBinding? = null
    private val binding: FragmentScrollBinding get() = _binding!!

    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

    private val viewModel: WeatherViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepoImpl())as T
            }
        }.create(WeatherViewModel::class.java)
    }

    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it ->
        it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScrollBinding.inflate(layoutInflater)

        city = city?.capitalizeWords()

        binding.tvCityTitle.text = city

        binding.vwTitleBar.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        viewModel.getForecast(city)
        configureObserver()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureObserver() {
        weatherRecyclerAdapter = WeatherRecyclerAdapter(openDetails = ::openDetails)

        viewModel.forecast.observe(viewLifecycleOwner) { response ->
            if (response.list.isNotEmpty()) {
                weatherRecyclerAdapter.setForecast(response.list)
                binding.rvForecastList.adapter = weatherRecyclerAdapter
            }
        }
    }

    private fun openDetails(weatherListItem: WeatherListItem) {
        parentFragmentManager.beginTransaction()
            .replace(com.superspecialapp.sameermonirweatherapp.R.id.fr_container, DetailsFragment.newInstance(weatherListItem, city))
            .addToBackStack(null)
            .commit()
    }
}