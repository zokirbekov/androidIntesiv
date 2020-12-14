package ru.mikhailskiy.intensiv

import io.reactivex.Observable
import java.util.*

fun main()
{

}

data class Book(
    val title: String,
    val author: String,
    val id: Long?,
    val price: Double,
    val location: String,
    val currency: String
)

class CodeLab()
{
    fun filterBook()
    {
        val dis = Observable.fromIterable(getBooksList())
            .filter { it.location == "Москва"}
            .filter {it.price > 400}
            .distinct { it.title }
            .map { Pair(it.title, it.author) }
            .subscribe { println("${it.first} ${it.second}") }
    }

    private fun getBooksList(): List<Book> {
        return listOf(
            Book("Шантарам", "Грегори Дэвид Робертс", 1, 580.0, "Москва", "₽"),
            Book("Шантарам", "Грегори Дэвид Робертс", 1, 680.0, "Москва", "₽"),
            Book("Шантарам", "Грегори Дэвид Робертс", 1, 780.0, "Москва", "₽"),
            Book("Три товарища", "Эрих Мария Ремарк", 2, 480.0, "Москва", "₽"),
            Book("Цветы для Элджернона", "Даниел Киз", 3, 380.0, "Москва", "₽"),
            Book(" Атлант расправил плечи", "Айн Рэнд", 4, 880.0, "Ставрополь", "₽"),
            Book(" Атлант расправил плечи", "Айн Рэнд", 4, 580.0, "Сочи", "₽")
        )
    }
}