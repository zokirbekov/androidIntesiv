package ru.mikhailskiy.intensiv.mapper

import ru.mikhailskiy.intensiv.data.company.ProductionCompaniesDto
import ru.mikhailskiy.intensiv.data.company.ProductionCompaniesEntity
import ru.mikhailskiy.intensiv.data.country.ProductionCountriesDto
import ru.mikhailskiy.intensiv.data.country.ProductionCountriesEntity

object ProCompanyMapper {
    fun dtoToEntity(productionCompaniesDto: ProductionCompaniesDto) =
        ProductionCompaniesEntity(
            proCompanyId = productionCompaniesDto.id ?: 0,
            name = productionCompaniesDto.name,
            logoPath = productionCompaniesDto.logoPath,
            originCountry = productionCompaniesDto.originCountry
        )

    fun entityToDto(productionCompaniesEntity: ProductionCompaniesEntity) =
        ProductionCompaniesDto(
            id = productionCompaniesEntity.proCompanyId,
            name = productionCompaniesEntity.name,
            logoPath = productionCompaniesEntity.logoPath,
            originCountry = productionCompaniesEntity.originCountry
        )
}