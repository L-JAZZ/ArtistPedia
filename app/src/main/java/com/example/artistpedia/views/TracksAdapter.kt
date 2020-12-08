package com.example.artistpedia.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistpedia.R
import com.example.artistpedia.networking.Track
import kotlinx.android.synthetic.main.row_tracks.view.*

class TracksAdapter (
    private val tracks:List<Track> = listOf()
    ): RecyclerView.Adapter<TracksAdapter.TracksViewHolder>() {

    class TracksViewHolder(private val views: View):RecyclerView.ViewHolder(views) {

        fun bindItem(track: Track){


            //если вытаскивать остаток от деления то не будут видны ноли после точки, будет типа 4:9
            //но чет не сработало
            /*val time =(track.intDuration/(1000*60)).toString().split(".".toRegex())[0]*/

            views.textViewSongName.text = track.strTrack

            views.txt_minute.text = (track.intDuration/(1000*60)).toString()

            views.txt_second.text = (track.intDuration/(1000)%60).toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TracksViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_tracks, parent, false))

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holderTracks: TracksViewHolder, position: Int) = holderTracks.bindItem(tracks[position])

}