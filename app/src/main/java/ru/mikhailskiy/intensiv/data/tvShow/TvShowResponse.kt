package ru.mikhailskiy.intensiv.data.tvShow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    val results:List<TvShow>?,
    @SerializedName("total_results")
    val totalResults:Int?,
    @SerializedName("total_pages")
    val totalPages:Int?,
    val page:Int?
)