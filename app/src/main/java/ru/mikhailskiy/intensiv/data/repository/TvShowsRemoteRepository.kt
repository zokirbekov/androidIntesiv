package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.Response
import ru.mikhailskiy.intensiv.data.mappers.TvShowMapper
import ru.mikhailskiy.intensiv.data.network.client.TvShowApiClient
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo
import ru.mikhailskiy.intensiv.domain.repository.TvShowsRepository
import java.lang.Exception

class TvShowsRemoteRepository : TvShowsRepository {
    override suspend fun getTvShows(page:Int) : Response {
        try {
            val response = TvShowApiClient.api.getPopular(page)
            return if (response.isSuccessful && response.body() != null) {
                Response.Success(TvShowMapper.dtoToVo(response.body()!!))
            }
            else
                Response.Error(response.message(), response.code())
        }
        catch (e:Exception) {
            e.printStackTrace()
            return Response.Error("Exception", -99)
        }
    }
}