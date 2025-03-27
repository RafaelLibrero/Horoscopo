package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.HoroscopeAdapter
import com.example.horoscopo.R
import com.example.horoscopo.data.HoroscopeProvider

class MainActivity : AppCompatActivity() {

    private lateinit var rvHoroscope: RecyclerView
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    private val horoscopeList = HoroscopeProvider.getHoroscopeList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initComponents() {
        rvHoroscope = findViewById(R.id.rvHoroscope)
    }

    private fun initUI() {
        horoscopeAdapter = HoroscopeAdapter(horoscopeList) { onItemSelected(it) }
        rvHoroscope.layoutManager = LinearLayoutManager(this)
        rvHoroscope.adapter = horoscopeAdapter
    }

    private fun onItemSelected(position: Int) {
        val horoscope = horoscopeList[position]
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("HOROSCOPE_ID",horoscope.id)
        startActivity(intent)

    }

}