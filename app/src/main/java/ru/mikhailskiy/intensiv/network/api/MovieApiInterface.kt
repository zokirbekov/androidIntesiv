package ru.mikhailskiy.intensiv.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mikhailskiy.intensiv.BuildConfig
import ru.mikhailskiy.intensiv.data.credit.CreditsResponse
import ru.mikhailskiy.intensiv.data.movie.MovieResponse
import ru.mikhailskiy.intensiv.data.movie.detail.MovieDetail
import ru.mikhailskiy.intensiv.manager.LanguageManager


interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("page") page: Int,
        @Query("language") language: String = LanguageManager.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("page") page: Int,
        @Query("language") language: String = LanguageManager.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getPopular(
        @Query("page") page: Int,
        @Query("language") language: String = LanguageManager.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = LanguageManager.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Call<MovieDetail>

    @GET("movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = LanguageManager.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Call<CreditsResponse>
}