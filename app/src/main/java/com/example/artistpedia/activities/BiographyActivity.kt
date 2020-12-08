package com.example.artistpedia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.loader.ArtistLoader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_biography.*

//говорящее название
class BiographyActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biography)
        val name = intent.getStringExtra(EXTRA_DATA)
        loadArtist(name)
    }

    private fun loadArtist(name:String?){
        ArtistLoader(
            onSuccess = {
                val artists = it.artists
                if (!artists.isNullOrEmpty()) {
                    val artist = it.artists[0]

                    txt_bio.text = artist.strBiographyEN
                    Picasso.get().load(artist.strArtistLogo).into(imageLogo)
                } else Toast.makeText(this, "Array of artists is empty", Toast.LENGTH_LONG).show()
            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        ).loadArtists(name!!)
    }
}
