package com.example.horoscopo

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.data.Horoscope

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ivIcon : AppCompatImageView = view.findViewById(R.id.ivIcon)
    private val tvName : TextView = view.findViewById(R.id.tvName)
    private val tvDates : TextView = view.findViewById(R.id.tvDates)

    fun render(horoscope: Horoscope) {
        ivIcon.setImageResource(horoscope.icon)
        tvName.setText(horoscope.name)
        tvDates.setText(horoscope.dates)
    }
}