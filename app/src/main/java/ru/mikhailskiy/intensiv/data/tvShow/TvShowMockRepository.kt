package ru.mikhailskiy.intensiv.data.tvShow

import ru.mikhailskiy.intensiv.data.movie.Movie

object TvShowMockRepository {
    fun getMovies(): List<TvShow> {

        val moviesList = mutableListOf<TvShow>()
        for (x in 0..10) {
            val movie = TvShow(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x
            )
            moviesList.add(movie)
        }

        return moviesList
    }
}