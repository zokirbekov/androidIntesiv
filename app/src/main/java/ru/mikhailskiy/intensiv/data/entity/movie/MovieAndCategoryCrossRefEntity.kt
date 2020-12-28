package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "categoryId"])
data class MovieAndCategoryCrossRefEntity(
    val categoryId:Long,
    val movieId:Long
)