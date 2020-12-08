package com.example.artistpedia.loader

import com.example.artistpedia.networking.ApiFactory
import com.example.artistpedia.networking.TracksList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TracksLoader(
    private val onSuccess:(TracksList)->Unit,
    private val onError:(Throwable)->Unit
) {
    fun loadTracks(name:String){
        ApiFactory.getApiClient().getTracksList(name).enqueue(object : Callback<TracksList> {

            override fun onResponse(call: Call<TracksList>, response: Response<TracksList>) {
                onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<TracksList>, t: Throwable) {
                onError(t)
            }

        })
    }
}