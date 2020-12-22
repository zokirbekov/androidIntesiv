package ru.mikhailskiy.intensiv.data

import com.google.gson.annotations.SerializedName

open class BaseMovieDto {

    var id:Long? = null

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0

    @SerializedName("poster_path")
    var posterPath:String? = null

    var overview:String? = null

    @SerializedName("genre_ids")
    var genreIds:IntArray? = null

    @SerializedName("original_language")
    var originalLanguage:String? = null

    @SerializedName("vote_count")
    var voteCount:Int? = null

    @SerializedName("backdrop_path")
    var backdropPath:String? = null

    var popularity:Double? = null

    val rating: Float
        get() = voteAverage.div(2).toFloat()
}