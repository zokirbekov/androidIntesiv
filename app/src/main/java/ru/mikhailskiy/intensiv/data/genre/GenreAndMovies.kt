package ru.mikhailskiy.intensiv.data.genre

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.movie.MovieAndGenreCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieEntity

data class GenreAndMovies(
    @Embedded
    val movie: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(
            MovieAndGenreCrossRef::class,
            parentColumn = "genreId",
            entityColumn = "movieId"
        )
    )
    val genres:List<MovieEntity>
)