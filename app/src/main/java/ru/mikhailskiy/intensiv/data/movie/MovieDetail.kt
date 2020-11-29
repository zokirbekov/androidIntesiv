package ru.mikhailskiy.intensiv.data.movie

import ru.mikhailskiy.intensiv.data.actor.Actor

class MovieDetail(
    title: String = "",
    voteAverage: Double = 0.0,
    val imageUrl:String,
    val movieQuality:String,
    val description: String,
    val studio: String,
    val genre: String,
    val year: String,
    val actors: List<Actor>
) : Movie(title, voteAverage) {
}