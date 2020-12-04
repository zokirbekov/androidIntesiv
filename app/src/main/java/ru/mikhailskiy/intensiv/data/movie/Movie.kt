package ru.mikhailskiy.intensiv.data.movie

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.BaseMovie

open class Movie : BaseMovie() {

    var title: String? = ""

    var adult:Boolean? = null

    @SerializedName("release_date")
    var releaseDate:String? = null

    @SerializedName("original_title")
    var originalTitle:String? = null

    var video:Boolean? = null

}
