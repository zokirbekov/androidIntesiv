package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.Response
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo

interface TvShowsRepository {
    suspend fun getTvShows(page:Int = 1) : Response
}