package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.entity.GenreEntity

data class MovieAndGenresEntity(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(
            MovieAndGenreCrossRefEntity::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genres:List<GenreEntity>
)