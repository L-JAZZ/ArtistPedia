package com.example.artistpedia.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.models.User
import com.example.artistpedia.networking.ArtistInfo
import com.example.artistpedia.views.FavoriteAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private val database by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        r_view_favorite.layoutManager = FlexboxLayoutManager(this)

        if (auth.currentUser != null){
            loadFavorite(auth!!.currentUser!!.uid)
        }else Toast.makeText(this, "You are not logged in!", Toast.LENGTH_LONG)

    }

    private fun loadFavorite(uid: String){
        database.collection("users")
            .document(uid)
            .addSnapshotListener{snapshot, e->
                if (e != null){
                    return@addSnapshotListener
                }
                val user = snapshot?.toObject(User::class.java)
                if (user != null) {
                    displayFavArtists(user.artists)
                }
            }
    }

    private fun displayFavArtists(artists: List<ArtistInfo>){
        r_view_favorite.adapter = FavoriteAdapter(
            artistList = artists, onClick = {
                val intent = Intent(this, ArtistPageActivity::class.java)
                intent.putExtra("data", it.strArtist)
                startActivity(intent)
            })
    }
}
