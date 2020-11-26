package ru.mikhailskiy.intensiv.data.movie

import ru.mikhailskiy.intensiv.data.actor.Actor
import ru.mikhailskiy.intensiv.data.actor.ActorMockRepository

object MovieDetailMockRepository {
    fun getDetail() = MovieDetail(
        "Aquaman",
        4.5,
        "https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg",
        "4K",
        "In 1985 Maine, lighthouse keeper Thomas Curry rescues Atlanna, the queen of the underwater nation of Atlantis, during a storm. They eventually fall in love and have a son named Arthur, who is born with the power to communicate with marine lifeforms.",
        "Warner Bros",
        "Action, Adventure, Fantasy",
        "2018",
        ActorMockRepository.getActors()
    )
}