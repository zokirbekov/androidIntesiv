package ru.mikhailskiy.intensiv.mapper

import ru.mikhailskiy.intensiv.data.country.ProductionCountriesDto
import ru.mikhailskiy.intensiv.data.country.ProductionCountriesEntity

object ProCountriesMapper {

    fun dtoToEntity(productionCountriesDto: ProductionCountriesDto) =
        ProductionCountriesEntity(
            isoName = productionCountriesDto.isoName ?: "",
            name = productionCountriesDto.name
        )

    fun entityToDto(productionCountriesEntity: ProductionCountriesEntity) =
        ProductionCountriesDto(
            isoName = productionCountriesEntity.isoName,
            name = productionCountriesEntity.name
        )
}