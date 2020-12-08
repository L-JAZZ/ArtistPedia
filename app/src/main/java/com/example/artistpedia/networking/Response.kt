package com.example.artistpedia.networking

data class ArtistInfo(
    val intBornYear: Int,
    val strGenre:String,
    val strWebsite:String,
    val strBiographyEN:String,
    val strArtistThumb: String,
    val strArtistLogo: String,
    val idArtist:Int,
    val strArtist: String
){
    constructor():this(0,"","","","","",0,"")
}

data class ArtistsList(
    val artists: List<ArtistInfo>
)

data class AlbumInfo(
    val idAlbum:Int,
    val idArtist: Int,
    val strAlbum:String,
    val strArtist:String,
    val strAlbumThumb:String,
    val strDescriptionEN:String,
    val intYearReleased:Int,
    val strGenre:String
)

data class ArtistAlbums(
    val album: List<AlbumInfo>
)

data class Track(
    val intDuration: Int,
    val strTrack: String,
    val strTrackThumb: String,
    val strMusicVid: String?,
    val strAlbum: String
)

data class TracksList(
    val track: List<Track>
)

data class Video(
    val idArtist: Int,
    val strTrack:String,
    val strTrackThumb:String,
    val strMusicVid:String
)

data class VideoList(
    val mvids:List<Video>
)

