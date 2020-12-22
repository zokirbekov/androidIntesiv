package ru.mikhailskiy.intensiv.data.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    val genreId: Long,
    val genreName: String?
)