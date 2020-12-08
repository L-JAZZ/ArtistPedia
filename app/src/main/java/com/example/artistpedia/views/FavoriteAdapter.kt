package com.example.artistpedia.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistpedia.R
import com.example.artistpedia.networking.ArtistInfo

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_page.view.*
import kotlinx.android.synthetic.main.row_favorite.view.*


class FavoriteAdapter(
    private val artistList: List<ArtistInfo>,
    private val onClick: (ArtistInfo) -> Unit)
    : RecyclerView.Adapter<FavoriteAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_favorite, parent, false))

    override fun getItemCount() = artistList.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(artistList[position], onClick)


    class EventsHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun binding(artistList: ArtistInfo, onClick: (ArtistInfo) -> Unit){
            view.txt_artistName.text = artistList.strArtist
            Picasso.get().load(artistList.strArtistThumb).into(view.img_artist_logo)

            view.setOnClickListener{
                onClick(artistList)
            }
        }
    }
}