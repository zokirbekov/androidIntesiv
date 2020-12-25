package ru.mikhailskiy.intensiv.data.dto

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesDto(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)