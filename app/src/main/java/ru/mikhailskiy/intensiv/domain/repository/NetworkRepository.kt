package ru.mikhailskiy.intensiv.domain.repository

interface NetworkRepository {
    fun isAvailable() : Boolean
}