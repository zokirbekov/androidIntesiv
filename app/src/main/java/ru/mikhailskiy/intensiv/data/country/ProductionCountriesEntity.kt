package ru.mikhailskiy.intensiv.data.country

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ProductionCountriesEntity(
    @PrimaryKey
    var isoName:String,
    var name:String?
)