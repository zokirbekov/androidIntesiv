package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieAndFavorite(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movId"
    )
    val favorite:FavoriteMovieEntity?
)