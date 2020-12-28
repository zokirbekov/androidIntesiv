package ru.mikhailskiy.intensiv.data.dto.tvShow

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.dto.BaseMovieDto

data class TvShowDto(

    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("original_name")
    val originalName: String?,
    val name: String?

) : BaseMovieDto()