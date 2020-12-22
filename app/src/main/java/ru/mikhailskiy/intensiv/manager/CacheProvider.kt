package ru.mikhailskiy.intensiv.manager

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class CacheProvider<T>(private val uiScheduler:Scheduler, private val backgroundScheduler: Scheduler) {
    fun getObservable(): Observable<T> =
        createObservable()
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)

    private fun createObservable() : Observable<T>{
        val remoteObservable = createRemoteObservable()

        return createLocaleObservable()
            .onErrorResumeNext(remoteObservable)
            .switchIfEmpty(remoteObservable)
    }

    protected abstract fun createRemoteObservable() : Observable<T>

    protected abstract fun createLocaleObservable() : Observable<T>
}