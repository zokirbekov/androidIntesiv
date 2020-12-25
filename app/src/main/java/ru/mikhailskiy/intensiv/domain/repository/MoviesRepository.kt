package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo

interface MoviesRepository {
    fun getMovies(page:Int) : Single<List<MovieVo>>
}