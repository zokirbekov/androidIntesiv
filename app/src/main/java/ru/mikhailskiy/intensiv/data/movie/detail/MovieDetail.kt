package ru.mikhailskiy.intensiv.data.movie.detail

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.company.ProductionCompaniesEntity
import ru.mikhailskiy.intensiv.data.country.ProductionCountriesEntity
import ru.mikhailskiy.intensiv.data.genre.GenreEntity
import ru.mikhailskiy.intensiv.data.movie.MovieAndGenreCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieAndProCompanyCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieAndProCountryCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieEntity

data class MovieDetail(

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
    val genre: List<GenreEntity>,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "proCompanyId",
        associateBy = Junction(
            MovieAndProCompanyCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "proCompanyId"
        )
    )
    val productionCompanies: List<ProductionCompaniesEntity>,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "isoName",
        associateBy = Junction(
            MovieAndProCountryCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "isoName"
        )
    )
    val productionCountries: List<ProductionCountriesEntity>?
)