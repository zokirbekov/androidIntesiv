package ru.mikhailskiy.intensiv.domain.useCase

import ru.mikhailskiy.intensiv.domain.repository.TvShowsRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers

class TvShowsUseCase(private val tvShowsRepository: TvShowsRepository) {
    fun getTvShows(page:Int = 1) = tvShowsRepository
        .getTvShows(page)
        .applySchedulers()
}