package ru.mikhailskiy.intensiv.data.dto.movie

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.dto.BaseMovieDto

open class MovieDto : BaseMovieDto() {

    var title: String? = ""

    var adult:Boolean? = null

    @SerializedName("release_date")
    var releaseDate:String? = null

    @SerializedName("original_title")
    var originalTitle:String? = null

    var video:Boolean? = null

}
