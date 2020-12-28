package ru.mikhailskiy.intensiv.data.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mikhailskiy.intensiv.BuildConfig
import ru.mikhailskiy.intensiv.data.dto.tvShow.TvShowResponse
import ru.mikhailskiy.intensiv.data.repository.LanguageRepository

interface TvShowApiInterface {
    @GET("tv/popular")
    fun getPopular(
        @Query("page") page: Int = 1,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Single<TvShowResponse>
}