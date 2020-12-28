package ru.mikhailskiy.intensiv.domain.useCase

import ru.mikhailskiy.intensiv.domain.repository.CreditRepository
import ru.mikhailskiy.intensiv.domain.repository.MovieDetailRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers

class MovieDetailUseCase(private val movieDetailRepository: MovieDetailRepository) {
    fun getDetail(movieId: Int) = movieDetailRepository.getDetail(movieId).applySchedulers()

}