package ru.mikhailskiy.intensiv.data.network.client

import ru.mikhailskiy.intensiv.data.network.BaseClient
import ru.mikhailskiy.intensiv.data.network.api.TvShowApiInterface

object TvShowApiClient : BaseClient<TvShowApiInterface>(TvShowApiInterface::class.java) {

}