package com.example.artistpedia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.loader.AlbumDetailLoader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
        val name = intent.getStringExtra("name")!!
        loadDetails(name)
    }

    private fun loadDetails(name:String){
        AlbumDetailLoader(
            onSuccess = {
                val albumDetail = it.album[0]
                //возвращает первый по совпадениям
                if (it.album[0].idAlbum==null){
                    Toast.makeText(this, "This album doesn't have details, please go back", Toast.LENGTH_LONG).show()
                }
                if( !albumDetail.strAlbumThumb.isNullOrEmpty()){
                    Picasso.get().load(albumDetail.strAlbumThumb).into(imageView_album)
                }else Picasso.get().load("https://bitsofco.de/content/images/2018/12/broken-1.png")

                albumName.text = albumDetail.strAlbum
                artistName.text = albumDetail.strArtist
                txt_album_desc.text = albumDetail.strDescriptionEN

                //да, замудренный интент
                goToTracks(it.album[0].idAlbum.toString())
            },
            onError = {
                Log.d("plswork", it.message!!)
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            },
            key = name
        ).loadAlbums()
    }

    private fun goToTracks(id:String){
        goToAllTracks.setOnClickListener {
            val intent = Intent(this, AlbumTracksActivity::class.java)
            intent.putExtra("idtrack",id)
            intent.putExtra("albumname",albumName.text)
            startActivity(intent)
        }
    }
}
