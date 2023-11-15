package online.muhammadali.todolist.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import online.muhammadali.todolist.feature_main.data.source.LocalDatabase
import online.muhammadali.todolist.feature_main.data.source.Task
import online.muhammadali.todolist.feature_main.data.source.TasksDAO
import online.muhammadali.todolist.util.TestExecutor
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


infix fun Task.matches(otherTask: Task) =
    title == otherTask.title &&
    description == otherTask.description &&
    priority == otherTask.priority &&
    completeness == otherTask.completeness &&
    parentId == otherTask.parentId



@RunWith(JUnit4::class)
class LocalDatabaseTest : TestExecutor() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var db: LocalDatabase
    private lateinit var dao: TasksDAO

    override fun setup() {
        db = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java,
            "tasks test"
        )
            .allowMainThreadQueries()
            .build()

        dao = db.getTaskDAO()

    }

    override suspend fun tearUp() {
        dao.clearAllTasks()
        db.close()
    }



    private val testTask = Task(
        title = "test task",
        description = "test description",
        completeness = 0.5f,
        priority = 1
    )

    @Test
    fun success_when_all_read_all_tasks() = executeTest {
        val id = dao.addNewTask(testTask)
        val tasks = dao.getAllTasks().first()

        assertThat(tasks.size).isEqualTo(1)
        assertThat(tasks[0] matches testTask.copy(taskId = id)).isTrue()
    }

    //  Can be a sub or parent task
    @Test
    fun success_when_create_task() = executeTest {
        val id = dao.addNewTask(testTask)
        val task = dao.getTaskById(id).first()

        assertThat(task matches testTask)
    }

    @Test
    fun success_when_update_task() = executeTest {
        val id = dao.addNewTask(testTask)
        val task = dao.getTaskById(id).first().copy(title = "updated title")

        dao.updateTask(task.copy(taskId = id))

        assertThat(task matches testTask)
    }

    @Test
    fun success_when_delete_task() = executeTest {
        val id = dao.addNewTask(testTask)
        dao.deleteTask(testTask.copy(taskId = id))

        val allTasks = dao.getAllTasks().first()

        println("Result: $allTasks" )

        assertThat(allTasks.isEmpty()).isTrue()

    }

    @Test
    fun success_when_read_all_tasks_with_common_parent_id() = executeTest {
        val tasks = mutableListOf<Task>()
        for (i in 0 .. 3 ) {

            val task = testTask.copy(
                title = "task where id: $i",
                parentId = if (i == 0) -1 else 0
            )
            val id = dao.addNewTask(task)
            tasks.add(task.copy(taskId = id))
        }

        val tasksResult = dao.getAllTasksWithCommonParent(0L).first()
        assertThat(tasksResult.contains(tasks[1]) && tasks.contains(tasks[2]) && tasks.contains(tasks[3])).isTrue()
    }


    @Test
    fun success_when_read_all_tasks_with_common_property() = executeTest {
        val tasks = mutableListOf<Task>()
        for (i in 0 .. 3 ) {

            val task = testTask.copy(
                title = "task where id: $i",
                priority = if (i == 0) -1 else 0
            )
            val id = dao.addNewTask(task)
            tasks.add(task.copy(taskId = id))
        }

        val tasksResult = dao.getAllTasksWithCommonPriority(0).first()
        assertThat(tasksResult.contains(tasks[1]) && tasks.contains(tasks[2]) && tasks.contains(tasks[3])).isTrue()
    }

    @Test
    fun success_when_read_all_completed_tests() = executeTest {
        val tasks = mutableListOf<Task>()
        for (i in 0 .. 3 ) {

            val task = testTask.copy(
                title = "task where id: $i",
                completeness = if (i == 0) .05f else 0f
            )
            val id = dao.addNewTask(task)
            tasks.add(task.copy(taskId = id))
        }

        val tasksResult = dao.getAllTasksWithCommonCompleteness(0f).first()

        println("Given: $tasks")
        println("Result: $tasksResult")
        assertThat(tasksResult.contains(tasks[1]) && tasks.contains(tasks[2]) && tasks.contains(tasks[3])).isTrue()
    }

    //  search in title
    @Test
    fun success_when_search_in_title() = executeTest {
        val tasks = mutableListOf<Task>()
        val searchKeyword = "search key"

        val wantedTask = testTask.copy(
            title = searchKeyword + "bla"
        )

        for (i in 0 .. 3 ) {

            val task = if (i == 0) wantedTask else testTask

            val id = dao.addNewTask(task)
            tasks.add(task.copy(taskId = id))
        }

        val tasksResult = dao.searchInTaskTitle(keyword = searchKeyword).first()
        assertThat(tasksResult.size).isEqualTo(1)
        assertThat(tasksResult[0] matches wantedTask)
    }

    @Test
    fun success_when_search_in_description() = executeTest {
        val tasks = mutableListOf<Task>()
        val searchKeyword = "search key"
        val wantedTask = testTask.copy(
            description = searchKeyword + "bla"
        )

        for (i in 0 .. 3 ) {

            val task = if (i == 0) wantedTask else testTask

            val id = dao.addNewTask(task)
            tasks.add(task.copy(taskId = id))
        }

        val tasksResult = dao.searchInTaskDescription(keyword = searchKeyword).first()
        assertThat(tasksResult.size).isEqualTo(1)
        assertThat(tasksResult[0] matches wantedTask)
    }

}