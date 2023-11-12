package online.muhammadali.todolist.feature_main.data.repository

import kotlinx.coroutines.flow.first
import online.muhammadali.todolist.feature_main.data.source.Task
import online.muhammadali.todolist.common.Error.getResultDbOperationAsync
import online.muhammadali.todolist.feature_main.data.source.TasksDAO

//  TODO Test

class TaskDbRepoImp (
    private val dao: TasksDAO
) : TaskDbRepo {

    override fun getAllTasks () = getResultDbOperationAsync {
        dao.getAllTasks().first()
    }

    override fun getTaskById (id: Long) = getResultDbOperationAsync {
        dao.getTaskById(id).first()
    }

    override fun updateTask (task: Task) = getResultDbOperationAsync {
        dao.updateTask(task)
    }

    override fun getAllSubtasks (parentId: Long) = getResultDbOperationAsync {
        dao.getAllTasksWithCommonParent(parentId).first()
    }

    override fun deleteTask (task: Task) = getResultDbOperationAsync {
        dao.deleteTask(task)
    }

    override fun createTask (task: Task) = getResultDbOperationAsync {
        dao.addNewTask(task)
    }

    override fun getTasksWithPriority (priority: Int) = getResultDbOperationAsync {
        dao.getAllTasksWithCommonPriority(priority).first()
    }

    override fun getTasksWithCompleteness (completeness: Float) = getResultDbOperationAsync {
        dao.getAllTasksWithCommonCompleteness(completeness).first()
    }

    override fun searchInTasksTitle (keyword: String) = getResultDbOperationAsync {
        dao.searchInTaskTitle(keyword).first()
    }

    override fun searchInTaskDescription (keyword: String) = getResultDbOperationAsync {
        dao.searchInTaskDescription(keyword).first()
    }
}