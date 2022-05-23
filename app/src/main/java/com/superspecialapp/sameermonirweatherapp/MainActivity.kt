package com.superspecialapp.sameermonirweatherapp

//import androidx.fragment.app.FragmentTransaction
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.superspecialapp.sameermonirweatherapp.view.SearchFragment

class MainActivity : AppCompatActivity() {



    companion object {
        var tempButton: ExtendedFloatingActionButton? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempButton = this.findViewById(R.id.add_fab)

        tempButton!!.setOnClickListener {
            val currentTemp = tempButton!!.text.toString()

            tempButton!!.text = when (currentTemp) {
                "°C" -> "°F"
                "°F" -> "°K"
                else -> "°C"
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.fr_container, SearchFragment()).commit()
    }


}


