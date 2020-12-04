package ru.mikhailskiy.intensiv.manager

import java.util.*

object LanguageManager {
    fun currentLanguage() = Locale.getDefault().language
}