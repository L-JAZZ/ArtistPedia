package com.example.artistpedia.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistpedia.R
import com.example.artistpedia.loader.VideoLoader
import com.example.artistpedia.networking.VideoList
import com.example.artistpedia.views.VideoAdapter
import kotlinx.android.synthetic.main.activity_clips.*
import java.lang.Exception

//видосы
class ClipsActivity : AppCompatActivity() {

    companion object{
        const val ID_DATA = "dataid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clips)
        r_view_videos.layoutManager=LinearLayoutManager(this)
        val artId = intent.getStringExtra(ID_DATA)
        loadVideo(artId)
    }

    private fun loadVideo(name:String?){
        VideoLoader(
            onSuccess = {response->
                showVideos(response)
                Log.d("plswork",response.mvids.toString())
            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        ).loadVideos(name!!)
    }

    private fun showVideos(response: VideoList){
        try {
            r_view_videos.adapter =
                VideoAdapter(
                    videos = response.mvids,
                    onClick = {videos->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videos.strMusicVid))
                        startActivity(intent)
                    }
                )
        }catch (e: Exception){
            Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show()
        }
    }
}
