package com.example.artistpedia.activities

import com.example.artistpedia.loader.TracksLoader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistpedia.R
import com.example.artistpedia.networking.TracksList
import com.example.artistpedia.views.TracksAdapter
import kotlinx.android.synthetic.main.activity_album_tracks.*
import java.lang.Exception

class AlbumTracksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_tracks)
        r_view_tracks.layoutManager = LinearLayoutManager(this)
        val name = intent.getStringExtra("idtrack")
        loadTracks(name!!)
    }
    private fun loadTracks(id:String){
        TracksLoader(
            onSuccess = {response->
                showTracks(response)
                Log.d("plswork", response.toString())
                track_album_name.text = intent.getStringExtra("albumname")
            },
            onError = {
                Log.d("plswork", it.message!!)
                Toast.makeText(this, "Ne Success", Toast.LENGTH_LONG).show()
            }
        ).loadTracks(id)
    }

    private fun showTracks(response: TracksList){
        try {
            r_view_tracks.adapter = TracksAdapter(tracks = response.track)

        }catch (e: Exception){
            Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show()
        }
    }
}
