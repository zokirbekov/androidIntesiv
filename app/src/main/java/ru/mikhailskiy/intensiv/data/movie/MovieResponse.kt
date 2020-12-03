package ru.mikhailskiy.intensiv.data.movie

import ru.mikhailskiy.intensiv.data.Dates

data class MovieResponse(
    val page:Int?,
    val results:List<Movie>?,
    val dates:Dates?
)