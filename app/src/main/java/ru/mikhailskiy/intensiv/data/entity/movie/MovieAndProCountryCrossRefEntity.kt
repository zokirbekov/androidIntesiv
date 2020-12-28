package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "isoName"])
data class MovieAndProCountryCrossRefEntity(
    val movieId:Long,
    val isoName:String
)