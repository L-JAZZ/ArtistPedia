package com.example.artistpedia.loader

import com.example.artistpedia.networking.ApiFactory
import com.example.artistpedia.networking.ArtistsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//load on search
class ArtistLoader(
    private val onSuccess:(ArtistsList)->Unit,
    private val onError:(Throwable)->Unit
) {
    fun loadArtists(name:String){
        ApiFactory.getApiClient().getArtistByName(name).enqueue(object : Callback<ArtistsList> {

            override fun onResponse(call: Call<ArtistsList>, response: Response<ArtistsList>) {
                onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ArtistsList>, t: Throwable) {
                onError(t)
            }

            })
    }
}