package ru.mikhailskiy.intensiv.data.vo

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesVo(
    val id: Long?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)