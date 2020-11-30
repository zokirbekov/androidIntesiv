package ru.mikhailskiy.intensiv.data

import com.google.gson.annotations.SerializedName

class ProductionCountries {
    @SerializedName("iso_3166_1")
    var isoName:String? = null

    var name:String? = null
}