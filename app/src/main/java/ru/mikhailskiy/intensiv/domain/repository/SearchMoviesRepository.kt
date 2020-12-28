package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo

interface SearchMoviesRepository {
    fun searchAndGetMovies(text:String, page:Int) : Observable<List<MovieVo>>
}