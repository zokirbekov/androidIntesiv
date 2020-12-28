package ru.mikhailskiy.intensiv.data.dto.movie

import ru.mikhailskiy.intensiv.data.dto.DatesDto

data class MovieResponse(
    val page:Int?,
    val results:List<MovieDto>?,
    val dates: DatesDto?
)