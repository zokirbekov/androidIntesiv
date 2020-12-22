package ru.mikhailskiy.intensiv.data.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.genre.GenreEntity

data class MovieAndGenres(
    @Embedded
    val movie:MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(
            MovieAndGenreCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genres:List<GenreEntity>
)