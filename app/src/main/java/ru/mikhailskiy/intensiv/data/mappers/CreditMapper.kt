package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.credit.CreditDto
import ru.mikhailskiy.intensiv.data.dto.credit.CreditsResponse
import ru.mikhailskiy.intensiv.data.vo.credit.CreditVo

object CreditMapper {
    fun dtoToVo(dto:CreditDto) =
        CreditVo(
            id = dto.id,
            name = dto.name,
            popularity = dto.popularity,
            profilePath = dto.profilePath
        )

    fun dtoToVo(dto:CreditsResponse) =
        dto.cast?.map {
            dtoToVo(it)
        }
}