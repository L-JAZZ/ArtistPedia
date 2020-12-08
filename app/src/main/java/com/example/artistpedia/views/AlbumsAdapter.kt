package com.example.artistpedia.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistpedia.R
import com.example.artistpedia.networking.AlbumInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_albums.view.*


class AlbumsAdapter(
    private val albums: List<AlbumInfo> = listOf(),
    private val onClick:(AlbumInfo) -> Unit)
    : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>(){

    inner class ViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

        fun bindItem(album: AlbumInfo){

                /*Log.d("plsworkkk", "V ALBUMS VSE NORM")*/
                view.textViewAlbumName.text = album.strAlbum

                if (!album.strAlbumThumb.isNullOrEmpty()) {
                    Picasso.get().load(album.strAlbumThumb).into(view.imageViewAlbumThumb)
                } else Picasso.get().load("https://bitsofco.de/content/images/2018/12/broken-1.png")
                    .into(view.imageViewAlbumThumb)
                view.setOnClickListener {
                    onClick(album)
                }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
         ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_albums, parent, false))

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(albums[position])

}