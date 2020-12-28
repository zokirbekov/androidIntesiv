package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mikhailskiy.intensiv.data.CacheProvider
import ru.mikhailskiy.intensiv.data.entity.movie.MovieAndCategoryCrossRefEntity
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MovieDbRepository
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository
import ru.mikhailskiy.intensiv.domain.repository.NetworkRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers
import timber.log.Timber

class NowplayingMoviesRepository(
    networkRepository: NetworkRepository,
    private val remoteRepository: MoviesRepository,
    private val localeRepository: MovieDbRepository
) : CacheProvider<List<MovieVo>>(
    networkRepository,
    AndroidSchedulers.mainThread(),
    Schedulers.io()
),
    MoviesRepository {

    override fun getMovies(page: Int): Observable<List<MovieVo>> =
        getObservable()

    override fun createRemoteObservable(): Observable<List<MovieVo>> = remoteRepository.getMovies(
        MoviesRepository.DEFAULT_PAGE
    )
        .doOnNext {
            localeRepository.saveAll(it.map(MovieMapper::voToEntity))
                .applySchedulers()
                .doAfterSuccess { moviesId ->
                    localeRepository.saveCategoriesAndMovies(moviesId.map { id ->
                        MovieAndCategoryCrossRefEntity(
                            CategoryRepository.CategoryType.NOW_PLAYING,
                            id
                        )
                    })
                        .applySchedulers()
                        .doOnError { error ->
                            Timber.e(error)
                        }
                        .subscribe()
                }
                .doOnError { error ->
                    Timber.e(error)
                }
                .subscribe()
        }

    override fun createLocaleObservable(): Observable<List<MovieVo>> = localeRepository.getMovies(
        MoviesRepository.DEFAULT_PAGE
    )
}