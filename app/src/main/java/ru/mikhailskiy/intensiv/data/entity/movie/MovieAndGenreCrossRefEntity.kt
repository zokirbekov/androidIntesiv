package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieAndGenreCrossRefEntity(
    val movieId:Long,
    val genreId:Long
)