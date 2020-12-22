package ru.mikhailskiy.intensiv.common.typeConvertors

import androidx.room.TypeConverter

class IntArrayTypeConverter {
    @TypeConverter
    fun arrayToString(array:IntArray?) = array?.joinToString(",")

    @TypeConverter
    fun stringToArray(string: String?) =
        string?.split(",")?.map {
            it.toInt()
        }
}