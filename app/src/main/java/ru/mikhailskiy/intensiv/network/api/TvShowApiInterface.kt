package ru.mikhailskiy.intensiv.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mikhailskiy.intensiv.BuildConfig
import ru.mikhailskiy.intensiv.data.tvShow.TvShow
import ru.mikhailskiy.intensiv.data.tvShow.TvShowResponse
import ru.mikhailskiy.intensiv.manager.LanguageManager

interface TvShowApiInterface {
    @GET("tv/popular")
    fun getPopular(
        @Query("page") page: Int,
        @Query("language") language: String = LanguageManager.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Call<TvShowResponse>
}