package ru.mikhailskiy.intensiv.data.movie.detail

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.Genre
import ru.mikhailskiy.intensiv.data.ProductionCompanies
import ru.mikhailskiy.intensiv.data.ProductionCountries
import ru.mikhailskiy.intensiv.data.movie.Movie

data class MovieDetail(
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanies>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries>?,
    val genre: List<Genre>?
) : Movie()