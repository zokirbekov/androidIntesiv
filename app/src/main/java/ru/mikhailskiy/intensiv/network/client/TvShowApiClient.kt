package ru.mikhailskiy.intensiv.network.client

import ru.mikhailskiy.intensiv.network.BaseClient
import ru.mikhailskiy.intensiv.network.api.TvShowApiInterface

object TvShowApiClient : BaseClient<TvShowApiInterface>(TvShowApiInterface::class.java) {

}