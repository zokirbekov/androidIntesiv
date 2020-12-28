package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.mappers.CreditMapper
import ru.mikhailskiy.intensiv.data.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.data.vo.credit.CreditVo
import ru.mikhailskiy.intensiv.domain.repository.CreditRepository

class CreditRemoteRepository : CreditRepository {
    override fun getCredits(movieId: Int): Single<List<CreditVo>> =
        MovieApiClient.api.getCredits(movieId)
            .map { CreditMapper.dtoToVo(it) }
}