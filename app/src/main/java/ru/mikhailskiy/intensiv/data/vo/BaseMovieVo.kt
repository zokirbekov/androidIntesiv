package ru.mikhailskiy.intensiv.data.vo

import com.google.gson.annotations.SerializedName

open class BaseMovieVo {

    var id:Long? = null

    var voteAverage: Double = 0.0
    var posterPath:String? = null
    var overview:String? = null
    var backdropPath:String? = null
    var popularity:Double? = null

    var isFavorite:Boolean = false

    val rating: Float
        get() = voteAverage.div(2).toFloat()
}