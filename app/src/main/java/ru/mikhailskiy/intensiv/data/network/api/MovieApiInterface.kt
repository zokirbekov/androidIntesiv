package ru.mikhailskiy.intensiv.data.network.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mikhailskiy.intensiv.BuildConfig
import ru.mikhailskiy.intensiv.data.dto.credit.CreditsResponse
import ru.mikhailskiy.intensiv.data.dto.movie.MovieResponse
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDetailDto
import ru.mikhailskiy.intensiv.data.repository.LanguageRepository


interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("page") page: Int = 1,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ): Observable<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("page") page: Int = 1,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ): Observable<MovieResponse>

    @GET("movie/popular")
    fun getPopular(
        @Query("page") page: Int = 1,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Observable<MovieDetailDto>

    @GET("movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Observable<CreditsResponse>

    @GET("search/movie")
    fun search(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = LanguageRepository.currentLanguage(),
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API
    ) : Observable<MovieResponse>
}
