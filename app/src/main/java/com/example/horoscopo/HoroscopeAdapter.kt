package com.example.horoscopo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.data.Horoscope

class HoroscopeAdapter(
    private var horoscopes : List<Horoscope>,
    private val onItemSelected: (Int) -> Unit
): RecyclerView.Adapter<HoroscopeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent,false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horoscopes.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopes[position])
        holder.itemView.setOnClickListener { onItemSelected(position) }
    }

    fun updateItems(items: List<Horoscope>) {
        horoscopes = items
        notifyDataSetChanged()
    }
}