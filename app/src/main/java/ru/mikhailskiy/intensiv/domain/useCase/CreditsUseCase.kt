package ru.mikhailskiy.intensiv.domain.useCase

import ru.mikhailskiy.intensiv.domain.repository.CreditRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers

class CreditsUseCase(private val creditRepository: CreditRepository) {
    fun getCredits(movieId:Int) = creditRepository.getCredits(movieId).applySchedulers()
}