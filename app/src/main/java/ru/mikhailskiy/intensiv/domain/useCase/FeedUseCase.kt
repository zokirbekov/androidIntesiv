package ru.mikhailskiy.intensiv.domain.useCase

import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.internal.functions.Functions
import okhttp3.internal.toImmutableList
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers

class FeedUseCase(private val moviesRepositories: HashMap<MovieCategories, MoviesRepository>) {

    companion object {
        val DEFAULT_PAGE = 1
    }

    fun getZippedMovies(): Observable<HashMap<MovieCategories, List<MovieVo>>> {

        val categories = moviesRepositories.keys
        val observers = moviesRepositories.values.map { it.getMovies(DEFAULT_PAGE).toObservable() }
            .toMutableList()
        return Observable
            .zip(observers,
                Function<Array<Any>, HashMap<MovieCategories, List<MovieVo>>> {
                    val hashMap = hashMapOf<MovieCategories, List<MovieVo>>()
                    for (i in it.indices) {
                        hashMap[categories.elementAt(i)] = it[i] as List<MovieVo>
                    }
                    hashMap
                })
            .applySchedulers()
    }

    enum class MovieCategories {
        UPCOMING,
        NOW_PLAYING,
        POPULAR
    }
}