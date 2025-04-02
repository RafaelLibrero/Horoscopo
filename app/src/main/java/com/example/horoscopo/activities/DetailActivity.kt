package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    private lateinit var ivIcon: ImageView
    private lateinit var tvId: TextView
    private lateinit var tvDates: TextView

    private lateinit var horoscope: Horoscope

    private lateinit var session: SessionManager
    private var isFavorite = false
    private lateinit var favoriteMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = SessionManager(this)

        val id = intent.getStringExtra("HOROSCOPE_ID")

        horoscope = HoroscopeProvider.getHoroscopeById(id)!!
        isFavorite = session.getFavoriteHoroscope() == horoscope.id

        initComponents()
        render()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)

        favoriteMenuItem = menu.findItem(R.id.menu_favorite)
        setFavoriteIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                if (isFavorite) {
                    session.setFavoriteHoroscope("")
                } else {
                    session.setFavoriteHoroscope(horoscope.id)
                }
                isFavorite = !isFavorite

                setFavoriteIcon()
                return true
            }

            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, horoscope.id)
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
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

    private fun setFavoriteIcon() {

        if (isFavorite) {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite_selected)
        } else {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite)
        }
    }
}