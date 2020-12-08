package com.example.artistpedia.models

import com.example.artistpedia.networking.ArtistInfo



data class User(val uid: String, val username: String, val email: String,val artists: List<ArtistInfo>){
  constructor() : this("", "", "", emptyList())
}