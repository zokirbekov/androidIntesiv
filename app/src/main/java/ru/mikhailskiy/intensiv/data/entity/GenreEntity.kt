package ru.mikhailskiy.intensiv.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    val genreId: Long,
    val genreName: String?
)