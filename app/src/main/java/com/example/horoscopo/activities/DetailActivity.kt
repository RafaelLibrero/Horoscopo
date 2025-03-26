package com.example.horoscopo.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider

class DetailActivity : AppCompatActivity() {

    private lateinit var ivIcon: ImageView
    private lateinit var tvId : TextView
    private lateinit var tvDates: TextView

    private lateinit var horoscope: Horoscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("HOROSCOPE_ID")

        horoscope = HoroscopeProvider.getHoroscopeById(id)!!

        initComponents()
        render()
    }

    private fun initComponents() {
        ivIcon = findViewById(R.id.ivIcon)
        tvId = findViewById(R.id.tvID)
        tvDates = findViewById(R.id.tvDates)
    }

    private fun render() {
        ivIcon.setImageResource(horoscope.icon)
        tvId.setText(horoscope.name)
        tvDates.setText(horoscope.dates)
    }
}