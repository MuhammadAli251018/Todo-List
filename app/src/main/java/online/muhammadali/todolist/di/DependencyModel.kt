package online.muhammadali.todolist.di

abstract class DependencyModel <T> {

    fun interface  Initializer <T> {
        fun init(): T
    }

    abstract val initializer: Initializer<T>

    private val dependency by lazy {
        initializer.init()
    }

    fun provide() = dependency
}