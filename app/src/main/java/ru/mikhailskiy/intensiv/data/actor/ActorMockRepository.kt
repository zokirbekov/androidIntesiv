package ru.mikhailskiy.intensiv.data.actor

object ActorMockRepository {
    fun getActors() = listOf<Actor>(
        Actor("Jason Momoa"),
        Actor("Jason Momoa"),
        Actor("Jason Momoa"),
        Actor("Jason Momoa")
    )
}