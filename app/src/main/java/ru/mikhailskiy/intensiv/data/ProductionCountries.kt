package ru.mikhailskiy.intensiv.data

import com.google.gson.annotations.SerializedName

data class ProductionCountries(
    @SerializedName("iso_3166_1")
    var isoName:String?,
    var name:String?
)