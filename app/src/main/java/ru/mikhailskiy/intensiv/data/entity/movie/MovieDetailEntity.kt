package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.entity.GenreEntity
import ru.mikhailskiy.intensiv.data.entity.ProductionCompaniesEntity
import ru.mikhailskiy.intensiv.data.entity.ProductionCountriesEntity

data class MovieDetailEntity(
    @Embedded
    val movie:MovieEntity,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(
            MovieAndGenreCrossRefEntity::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genre: List<GenreEntity>,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "proCompanyId",
        associateBy = Junction(
            MovieAndProCompanyCrossRefEntity::class,
            parentColumn = "movieId",
            entityColumn = "proCompanyId"
        )
    )
    val productionCompanies: List<ProductionCompaniesEntity>,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "isoName",
        associateBy = Junction(
            MovieAndProCountryCrossRefEntity::class,
            parentColumn = "movieId",
            entityColumn = "isoName"
        )
    )
    val productionCountries: List<ProductionCountriesEntity>?
)