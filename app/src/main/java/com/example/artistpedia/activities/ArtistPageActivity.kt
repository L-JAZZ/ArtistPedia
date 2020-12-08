package com.example.artistpedia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.loader.ArtistLoader
import com.example.artistpedia.networking.ArtistInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_page.*

//активити с подробной инфой и с переходами на Био, Видео, Альбомы,
class ArtistPageActivity : AppCompatActivity() {

    private val database by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    companion object{
        const val EXTRA_DATA = "data"
        const val ID_DATA = "dataid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_page)

        val name = intent.getStringExtra(EXTRA_DATA)


        loadArtist(name)
        addToFavorite(name!!)


        toBio()
        toClips()
        toAlbums()
    }

    private fun loadArtist(name:String?){
        ArtistLoader(
            onSuccess = {
                val artists = it.artists
                if (!artists.isNullOrEmpty()) {
                    Log.d("taag", artists.toString())
                    val artist = it.artists[0]
                    artistid.text = artist.idArtist.toString()
                    artistName.text = it.artists[0].strArtist
                    bornYear.text = artist.intBornYear.toString()
                    genre.text = artist.strGenre
                    artistWebsite.text = artist.strWebsite
                    Picasso.get().load(artist.strArtistThumb).into(imageWideThumb)

                } else Toast.makeText(this, "Artist not found", Toast.LENGTH_LONG).show()
            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        ).loadArtists(name!!)
    }

    private fun addToFavorite(name: String){
        ArtistLoader(
            onSuccess = {
                val artists =it.artists
                if (!artists.isNullOrEmpty()) {
                    Log.d("taag", artists.toString())
                    val artist = it.artists[0]
                    btn_add_to_favorite.setOnClickListener {
                        insertFavoriteInDatabase(artist)
                    }
                } else Toast.makeText(this, "Artist not found", Toast.LENGTH_LONG).show()
            },
            onError = {
                Toast.makeText(this, "Voobshe ne gruzit", Toast.LENGTH_LONG).show()
            }
        ).loadArtists(name)
    }
    private fun insertFavoriteInDatabase(
        favoriteArtistInfo: ArtistInfo
    ){
        database.collection("users")
            .document(auth.currentUser!!.uid)
            .update("artists", FieldValue.arrayUnion(favoriteArtistInfo)).addOnSuccessListener {
                Toast.makeText(this, "Added to Favorite!", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to add to Favorite", Toast.LENGTH_LONG).show()
            }
    }

    private fun toBio(){
        goToBiography.setOnClickListener {
            val intent = Intent(this, BiographyActivity::class.java)
            val text = artistName.text.toString()
            intent.putExtra(SearchActivity.EXTRA_DATA, text)
            startActivity(intent)
        }
    }


    private fun toClips(){
        goToClips.setOnClickListener {
            val intent = Intent(this, ClipsActivity::class.java)
            val text = artistid.text.toString()
            intent.putExtra(ID_DATA, text)
            startActivity(intent)
        }
    }

    private fun toAlbums(){
        goToAlbums.setOnClickListener {
            val intent = Intent(this,AlbumsListActivity::class.java)
            intent.putExtra("name",artistName.text.toString())
            startActivity(intent)
        }
    }
}
