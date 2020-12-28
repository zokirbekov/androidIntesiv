package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
open class MovieEntity {

    @PrimaryKey(autoGenerate = true)
    var movieId:Long = 0
    var voteAverage: Double = 0.0
    var posterPath:String? = null
    var overview:String? = null

    var originalLanguage:String? = null
    var voteCount:Int? = null
    var backdropPath:String? = null
    var popularity:Double? = null

    var title: String? = ""
    var adult:Boolean? = null
    var releaseDate:String? = null
    var originalTitle:String? = null
    var video:Boolean? = null

    var isFavorite:Boolean = false

}