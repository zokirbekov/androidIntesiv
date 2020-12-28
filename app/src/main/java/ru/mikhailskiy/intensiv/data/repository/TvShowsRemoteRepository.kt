package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.mappers.TvShowMapper
import ru.mikhailskiy.intensiv.data.network.client.TvShowApiClient
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo
import ru.mikhailskiy.intensiv.domain.repository.TvShowsRepository

class TvShowsRemoteRepository : TvShowsRepository {
    override fun getTvShows(page:Int): Single<List<TvShowVo>> =
        TvShowApiClient.api.getPopular(page)
            .map {
                TvShowMapper.dtoToVo(it)
            }
}