package ru.mikhailskiy.intensiv.data

import io.reactivex.Observable
import io.reactivex.Scheduler
import ru.mikhailskiy.intensiv.domain.repository.NetworkRepository
import timber.log.Timber

abstract class CacheProvider<T>(
    private val networkRepository: NetworkRepository,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) {
    fun getObservable(): Observable<T> =
        createObservable()
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)

    private fun createObservable(): Observable<T> {
        return if (networkRepository.isAvailable()) {
            Timber.d("From remote")
            createRemoteObservable()
        }
        else {
            Timber.d("From locale")
            createLocaleObservable()
        }
    }

    protected abstract fun createRemoteObservable(): Observable<T>

    protected abstract fun createLocaleObservable(): Observable<T>
}