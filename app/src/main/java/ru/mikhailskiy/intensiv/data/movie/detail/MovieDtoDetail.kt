package ru.mikhailskiy.intensiv.data.movie.detail

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.genre.GenreDto
import ru.mikhailskiy.intensiv.data.company.ProductionCompaniesDto
import ru.mikhailskiy.intensiv.data.country.ProductionCountriesDto
import ru.mikhailskiy.intensiv.data.movie.MovieDto

data class MovieDtoDetail(
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesDto>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesDto>?,
    val genre: List<GenreDto>?
) : MovieDto()