package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo

interface MoviesRepository {
    fun getMovies(page:Int) : Observable<List<MovieVo>>

    companion object {
        val DEFAULT_PAGE = 1
    }
}