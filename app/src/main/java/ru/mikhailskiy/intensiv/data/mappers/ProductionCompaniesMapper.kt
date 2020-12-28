package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.ProductionCompaniesDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDetailDto
import ru.mikhailskiy.intensiv.data.entity.ProductionCompaniesEntity
import ru.mikhailskiy.intensiv.data.vo.ProductionCompaniesVo

object ProductionCompaniesMapper {
    fun dtoToVo(dto: MovieDetailDto) = dto.productionCompanies?.map { dtoToVo(it) }

    fun dtoToVo(dto: ProductionCompaniesDto) = ProductionCompaniesVo(
        id = dto.id,
        name = dto.name,
        originCountry = dto.originCountry,
        logoPath = dto.logoPath
    )

    fun dtoToEntity(dto: ProductionCompaniesDto) =
        ProductionCompaniesEntity(
            proCompanyId = dto.id ?: 0,
            name = dto.name,
            originCountry = dto.originCountry,
            logoPath = dto.logoPath
        )

    fun entityToVo(entity: ProductionCompaniesEntity) = ProductionCompaniesVo(
        id = entity.proCompanyId,
        name = entity.name,
        originCountry = entity.originCountry,
        logoPath = entity.logoPath
    )

}