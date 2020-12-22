package ru.mikhailskiy.intensiv.useCase

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.observable.ObservableEmpty
import io.reactivex.schedulers.Schedulers
import ru.mikhailskiy.intensiv.data.MovieCategoryType
import ru.mikhailskiy.intensiv.data.movie.MovieDto
import ru.mikhailskiy.intensiv.data.movie.MovieEntity
import ru.mikhailskiy.intensiv.db.MovieDao
import ru.mikhailskiy.intensiv.manager.CacheProvider
import ru.mikhailskiy.intensiv.mapper.MovieMapper
import ru.mikhailskiy.intensiv.network.client.MovieApiClient

class MovieUseCase(private val remote: MovieApiClient, private val locale: MovieDao) :
    CacheProvider<List<MovieDto>?>(AndroidSchedulers.mainThread(), Schedulers.io()) {

    var categoryType: MovieCategoryType? = null

    private fun saveInLocale(items: List<MovieEntity>) =
        locale.saveAll(items)


    override fun createRemoteObservable(): Observable<List<MovieDto>?> {
        return when (categoryType) {
            MovieCategoryType.POPULAR -> remote.api.getPopular()
            MovieCategoryType.NOW_PLAYING -> remote.api.getNowPlaying()
            MovieCategoryType.UPCOMING -> remote.api.getUpcoming()
            null -> remote.api.getPopular()
        }.map {
            it.results
        }
            .toObservable()
    }

    override fun createLocaleObservable(): Observable<List<MovieDto>?> =
        locale.getAll()
            .map { items ->
                items.map {
                    MovieMapper.entityToDto(it)
                }
            }
            .flatMap {
                if (it.isEmpty())
                    Observable.empty<List<MovieDto>?>()
                else
                    Observable.just(it)
            }

}