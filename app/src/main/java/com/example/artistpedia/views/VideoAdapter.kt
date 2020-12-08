package com.example.artistpedia.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistpedia.R
import com.example.artistpedia.networking.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_clips.view.*

class VideoAdapter(
    private val videos: List<Video> = listOf(),
    private val onClick:(Video) -> Unit)
    : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    inner class VideoViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bindItem(video: Video){

            if(!video.strTrack.isNullOrEmpty()){
                view.video_name.text = video.strTrack
            }else view.video_name.text = "Null title"

            if(video.strTrackThumb.isNullOrEmpty()) {
                Picasso.get().load(video.strTrackThumb).into(view.video_thumb)
            }else Picasso.get().load("https://bitsofco.de/content/images/2018/12/broken-1.png")
                .into(view.video_thumb)

            view.setOnClickListener {
                onClick(video)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        VideoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_clips, parent, false))

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holderVideo: VideoViewHolder, position: Int) = holderVideo.bindItem(videos[position])

}