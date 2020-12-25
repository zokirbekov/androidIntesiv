package ru.mikhailskiy.intensiv.data.repository

import java.util.*

object LanguageRepository {
    fun currentLanguage() = Locale.getDefault().language
}