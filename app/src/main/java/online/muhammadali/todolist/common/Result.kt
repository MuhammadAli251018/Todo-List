package online.muhammadali.todolist.common

sealed class Result<T>

data class Success <T> (val data: T?) : Result<T>()

data class Failure <T> (val errorMessage: String) : Result<T>()