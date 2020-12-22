package ru.mikhailskiy.intensiv.data.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieAndGenreCrossRef(
    val movieId:Long,
    val genreId:Long
)