package ru.mikhailskiy.intensiv.data.network.client

import ru.mikhailskiy.intensiv.data.network.BaseClient
import ru.mikhailskiy.intensiv.data.network.api.MovieApiInterface

object MovieApiClient : BaseClient<MovieApiInterface>(MovieApiInterface::class.java) {

}
