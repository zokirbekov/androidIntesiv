package ru.mikhailskiy.intensiv.data.tvShow

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.BaseMovieDto

data class TvShow(

    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("original_name")
    val originalName: String?,
    val name: String?

) : BaseMovieDto()