package ru.mikhailskiy.intensiv.data.dto.movie

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.dto.GenreDto
import ru.mikhailskiy.intensiv.data.dto.ProductionCompaniesDto
import ru.mikhailskiy.intensiv.data.dto.ProductionCountriesDto

data class MovieDetailDto(
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesDto>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesDto>?,
    val genre: List<GenreDto>?
) : MovieDto()