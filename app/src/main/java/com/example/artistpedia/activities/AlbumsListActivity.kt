package com.example.artistpedia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.loader.AlbumLoader
import com.example.artistpedia.views.AlbumsAdapter
import com.example.artistpedia.networking.ArtistAlbums
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_albums_list.*
import java.lang.Exception

class AlbumsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_list)
        recyclerViewAlbums.layoutManager = FlexboxLayoutManager(this)
        val name =intent.getStringExtra("name")!!
        loadAlbums(name)
    }

    private fun loadAlbums(name:String){
        AlbumLoader(
            onSuccess = {response->
                showAlbums(response)
                Log.d("plswork", response.toString())
            },
            onError = {
                Log.d("plswork", it.message!!)
            },
            key = name
        ).loadAlbums()
    }

    private fun showAlbums(response: ArtistAlbums){
        try {
            recyclerViewAlbums.adapter =
                AlbumsAdapter(
                    albums = response.album,
                    onClick = { album ->
                        val intent = Intent(this, AlbumDetailsActivity::class.java)
                        intent.putExtra("name", album.strAlbum)
                        startActivity(intent)
                    })
        }catch (e: Exception){
            Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show()
        }
    }
}
