package ru.mikhailskiy.intensiv.domain.useCase

import io.reactivex.Observable
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository.Companion.DEFAULT_PAGE
import ru.mikhailskiy.intensiv.extension.applySchedulers

class FeedUseCase(private val moviesRepositories: HashMap<Long, MoviesRepository>) {

    fun getZippedMovies(): Observable<HashMap<Long, List<MovieVo>>> {

        val categories = moviesRepositories.keys
        val observers = moviesRepositories.values.map { it.getMovies(DEFAULT_PAGE) }
            .toMutableList()
        return Observable
            .zip(observers
            ) {
                val hashMap = hashMapOf<Long, List<MovieVo>>()
                for (i in it.indices) {
                    hashMap[categories.elementAt(i)] = it[i] as List<MovieVo>
                }
                hashMap
            }
            .applySchedulers()
    }
}