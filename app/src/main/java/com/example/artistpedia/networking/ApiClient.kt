package com.example.artistpedia.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

/*    @GET("artists")
    fun getAllDataByKey(@Query("key") key: String): Call<ArtistsList>*/

    @GET("search.php")
    fun getArtistByName(@Query("s")strArtist:String): Call<ArtistsList>

    @GET("mvid.php")
    fun getVideosByID(@Query("i")idArtist:String): Call<VideoList>

    @GET("searchalbum.php")
    fun getAlbumByArtistName(@Query("s") key: String): Call<ArtistAlbums>

    @GET("searchalbum.php")
    fun getAlbumByArtistNameDetails(@Query("a") key: String): Call<ArtistAlbums>

    @GET("track.php")
    fun getTracksList(@Query("m") key:String): Call<TracksList>
}