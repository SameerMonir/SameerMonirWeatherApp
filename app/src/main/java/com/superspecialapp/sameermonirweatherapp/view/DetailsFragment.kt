package com.superspecialapp.sameermonirweatherapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import com.bumptech.glide.Glide
//import com.superspecialapp.sameermonirweatherapp.MainActivity
import com.superspecialapp.sameermonirweatherapp.databinding.FragmentDetailsBinding
//import com.superspecialapp.sameermonirweatherapp.databinding.FragmentScrollBinding
import com.superspecialapp.sameermonirweatherapp.model.WeatherListItem

class DetailsFragment: Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding? get() = _binding

    private var city : String? = null

    companion object {
        const val KEY = "DETAILS"

        fun newInstance(weatherListItem: WeatherListItem, current_city : String?): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()

            fragment.city = current_city

            bundle.putParcelable(KEY, weatherListItem)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val weatherListItem: WeatherListItem? = arguments?.getParcelable(KEY)

        _binding = FragmentDetailsBinding.inflate(layoutInflater)

        binding?.tvCityTitle?.text = this.city

        binding?.tvDetailTemp?.text = weatherListItem?.getTemp()
        binding?.tvDetailsFeelsLike?.text = weatherListItem?.getFeelsLike()
        binding?.tvDetailsDescription?.text = weatherListItem?.getDetailedDescription()
        binding?.tvDetailsWeather?.text = weatherListItem?.getDescription()

        binding?.vwTitleBar?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}