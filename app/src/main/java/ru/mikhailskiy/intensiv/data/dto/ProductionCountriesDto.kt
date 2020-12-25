package ru.mikhailskiy.intensiv.data.dto

import com.google.gson.annotations.SerializedName

data class ProductionCountriesDto(
    @SerializedName("iso_3166_1")
    var isoName:String?,
    var name:String?
)