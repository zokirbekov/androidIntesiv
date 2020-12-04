package ru.mikhailskiy.intensiv.network.client

import ru.mikhailskiy.intensiv.network.BaseClient
import ru.mikhailskiy.intensiv.network.api.MovieApiInterface

object MovieApiClient : BaseClient<MovieApiInterface>(MovieApiInterface::class.java) {

}
