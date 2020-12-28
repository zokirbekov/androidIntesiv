package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.ProductionCountriesDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDetailDto
import ru.mikhailskiy.intensiv.data.vo.ProductionCountriesVo

object ProductionCountriesMapper{
    fun dtoToVo(dto: MovieDetailDto) = dto.productionCountries?.map { dtoToVo(it) }

    fun dtoToVo(dto: ProductionCountriesDto) = ProductionCountriesVo(
        isoName = dto.isoName,
        name = dto.name
    )

}