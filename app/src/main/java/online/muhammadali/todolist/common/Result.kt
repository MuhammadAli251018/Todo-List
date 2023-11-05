package online.muhammadali.todolist.common


enum class Status {
    Success,
    Failure,
   // Loading,
}

sealed class Result<T> {
    abstract val status: Status

    companion object {
        //fun loading() = Loading

        fun <T> success(data: T) = Success(data)

        fun <T>failure(exception: Throwable) = Failure<T>(exception)
    }

    fun handleData(
        onSuccess: (data: T) -> Unit,
        onFailure: (Throwable) -> Unit
    ) = when (status) {
        Status.Success -> onSuccess((this as Success).data)

        Status.Failure -> onFailure((this as Failure).exception)
    }

    fun getOrNull() =
        if (status == Status.Success)
            (this as Success).data
        else null

    fun getOrThrow() =
        if (status == Status.Success)
            (this as Success).data
        else throw Throwable() //   todo but the exception

}

/*data object Loading : Result<Nothing>() {
    override val status: Status = Status.Loading
}*/

data class Success <T> (val data: T) : Result<T>() {
    override val status: Status = Status.Success
}

data class Failure <T> (val exception: Throwable) : Result<T>() {
    override val status: Status = Status.Failure
}