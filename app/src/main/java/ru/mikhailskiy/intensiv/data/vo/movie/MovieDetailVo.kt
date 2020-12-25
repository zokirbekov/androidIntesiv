package ru.mikhailskiy.intensiv.data.vo.movie

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.dto.GenreDto
import ru.mikhailskiy.intensiv.data.dto.ProductionCompaniesDto
import ru.mikhailskiy.intensiv.data.dto.ProductionCountriesDto
import ru.mikhailskiy.intensiv.data.vo.GenreVo
import ru.mikhailskiy.intensiv.data.vo.ProductionCompaniesVo
import ru.mikhailskiy.intensiv.data.vo.ProductionCountriesVo

data class MovieDetailVo(
    val productionCompanies: List<ProductionCompaniesVo>?,
    val productionCountries: List<ProductionCountriesVo>?,
    val genre: List<GenreVo>?
) : MovieVo()