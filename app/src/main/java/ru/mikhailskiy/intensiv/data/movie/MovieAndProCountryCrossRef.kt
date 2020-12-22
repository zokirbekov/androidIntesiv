package ru.mikhailskiy.intensiv.data.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "isoName"])
data class MovieAndProCountryCrossRef(
    val movieId:Long,
    val isoName:String
)