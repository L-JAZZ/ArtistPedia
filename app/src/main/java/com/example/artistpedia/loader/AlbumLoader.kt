package com.example.artistpedia.loader

import com.example.artistpedia.networking.ApiFactory
import com.example.artistpedia.networking.ArtistAlbums
import com.example.artistpedia.networking.ArtistsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//load on search
class AlbumLoader(
    private val onSuccess:(ArtistAlbums)->Unit,
    private val onError:(Throwable)->Unit,
    private val key:String
) {
    fun loadAlbums(){
        ApiFactory.getApiClient().getAlbumByArtistName(key).enqueue(object : Callback<ArtistAlbums> {

            override fun onResponse(call: Call<ArtistAlbums>, response: Response<ArtistAlbums>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<ArtistAlbums>, t: Throwable) {
                onError(t)
            }
        })
    }
}