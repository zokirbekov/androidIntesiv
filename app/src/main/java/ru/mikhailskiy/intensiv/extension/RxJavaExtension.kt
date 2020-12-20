package ru.mikhailskiy.intensiv.extension

import android.widget.ProgressBar
import androidx.core.view.isVisible
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(
    subscribeOnScheduler: Scheduler = Schedulers.io(),
    observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()
) =
    this.subscribeOn(subscribeOnScheduler)
        .observeOn(observeOnScheduler)

fun <T> Single<T>.setProgressOnFinalAndOnSubscribe(
    progressBar: ProgressBar
) =
    this.doOnSubscribe { progressBar.isVisible = true }
        .doFinally { progressBar.isVisible = false }

fun <T> Observable<T>.setProgressOnFinalAndOnSubscribe(
    progressBar: ProgressBar
) =
    this.doOnSubscribe { progressBar.isVisible = true }
        .doFinally { progressBar.isVisible = false }
