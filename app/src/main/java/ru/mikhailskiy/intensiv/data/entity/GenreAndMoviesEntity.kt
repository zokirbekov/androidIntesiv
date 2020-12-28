package ru.mikhailskiy.intensiv.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.entity.movie.MovieAndGenreCrossRefEntity
import ru.mikhailskiy.intensiv.data.entity.movie.MovieEntity

data class GenreAndMoviesEntity(
    @Embedded
    val movie: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(
            MovieAndGenreCrossRefEntity::class,
            parentColumn = "genreId",
            entityColumn = "movieId"
        )
    )
    val genres:List<MovieEntity>
)