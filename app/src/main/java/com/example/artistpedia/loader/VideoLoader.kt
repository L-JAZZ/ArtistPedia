package com.example.artistpedia.loader

import com.example.artistpedia.networking.ApiFactory
import com.example.artistpedia.networking.ArtistsList
import com.example.artistpedia.networking.VideoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//load on search
class VideoLoader(
    private val onSuccess:(VideoList)->Unit,
    private val onError:(Throwable)->Unit
) {
    fun loadVideos(id:String){
        ApiFactory.getApiClient().getVideosByID(id).enqueue(object : Callback<VideoList> {

            override fun onResponse(call: Call<VideoList>, response: Response<VideoList>) {
                onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<VideoList>, t: Throwable) {
                onError(t)
            }

        })
    }
}