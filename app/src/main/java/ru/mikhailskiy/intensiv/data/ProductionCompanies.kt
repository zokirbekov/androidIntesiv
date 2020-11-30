package ru.mikhailskiy.intensiv.data

import com.google.gson.annotations.SerializedName

class ProductionCompanies {
    var id:Int? = null

    @SerializedName("logo_path")
    var logoPath:String? = null

    var name:String? = null

    @SerializedName("origin_country")
    var originCountry:String? = null
}