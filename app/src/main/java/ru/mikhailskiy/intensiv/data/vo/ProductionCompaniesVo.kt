package ru.mikhailskiy.intensiv.data.vo

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesVo(
    val id: Int?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)