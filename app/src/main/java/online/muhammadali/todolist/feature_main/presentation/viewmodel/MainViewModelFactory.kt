package online.muhammadali.todolist.feature_main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import online.muhammadali.todolist.feature_main.data.repository.TaskDbRepo

class MainViewModelFactory(
    private val dataRepo: TaskDbRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(MainViewModel::class.java))
                MainViewModel(dataRepo) as T
            else
                throw (IllegalArgumentException())

    }
}