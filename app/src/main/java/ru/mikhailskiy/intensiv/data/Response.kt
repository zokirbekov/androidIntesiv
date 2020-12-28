package ru.mikhailskiy.intensiv.data

sealed class Response {
    class Success<T>(val data:T) : Response()
    class Error(val message:String,val code:Int) : Response()
}