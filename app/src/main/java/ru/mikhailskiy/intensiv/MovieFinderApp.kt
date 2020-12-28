package ru.mikhailskiy.intensiv

import android.app.Application
import ru.mikhailskiy.intensiv.data.db.MovieDatabase
import ru.mikhailskiy.intensiv.data.repository.CategoryRepository
import ru.mikhailskiy.intensiv.domain.repository.MovieDbRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        val categoryDao = MovieDatabase.get(this).categoryDao()
        categoryDao.save(CategoryRepository.get())
            .applySchedulers()
            .subscribe()
        initDebugTools()
    }
    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: MovieFinderApp? = null
            private set
    }
}