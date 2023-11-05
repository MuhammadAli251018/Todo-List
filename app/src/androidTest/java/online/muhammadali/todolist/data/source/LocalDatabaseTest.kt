package online.muhammadali.todolist.data.source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


infix fun Task.matches(otherTask: Task) =
    title == otherTask.title &&
    description == otherTask.description &&
    priority == otherTask.priority &&
    completeness == otherTask.completeness &&
    parentId == otherTask.parentId

class LocalDatabaseTest {

    private lateinit var db: LocalDatabase
    private lateinit var dao: TasksDAO

    @Before
    fun setup() {
        db = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java,
            "tasks test"
        )
            .allowMainThreadQueries()
            .build()

        dao = db.getTaskDAO()
    }



    private val testTask = Task(
        title = "test task",
        description = "test description",
        completeness = 0.5f,
        priority = 1
    )

    @Test
    fun success_when_all_read_all_tasks() = runTest {
        val id = dao.addNewTask(testTask)
        val tasks = dao.getAllTasks().first()

        assertThat(tasks.size).isEqualTo(1)
        assertThat(tasks[0] matches testTask.copy(taskId = id)).isTrue()
    }

    //  Can be a sub or parent task
    @Test
    fun success_when_create_task() = runTest {
        val id = dao.addNewTask(testTask)
        val task = dao.getTaskById(id).first()

        assertThat(task matches testTask)
    }

    @Test
    fun success_when_update_task() = runTest {
        val id = dao.addNewTask(testTask)
        val task = dao.getTaskById(id).first().copy(title = "updated title")

        dao.updateTask(task)

        assertThat(task matches testTask)
    }

    @Test
    fun success_when_delete_task() = runTest {
        val id = dao.addNewTask(testTask)
        dao.deleteTask(testTask)

        val allTasks = dao.getAllTasks().first()

        assertThat(allTasks.isEmpty()).isTrue()

    }

    @Test
    fun success_when_read_all_tasks_with_common_parent_id() = runTest {
        val tasks = mutableListOf<Task>()
        for (i in 0 .. 3 ) {

            val task = testTask.copy(
                title = "task where id: $i",
                parentId = if (i == 0) -1 else 0
            )
            dao.addNewTask(task)
            tasks.add(task)
        }

        val tasksResult = dao.getAllTasksWithCommonParent(0L).first()
        assertThat(tasksResult.contains(tasks[1]) && tasks.contains(tasks[2]) && tasks.contains(tasks[3])).isTrue()
    }


    @Test
    fun success_when_read_all_tasks_with_common_property() = runTest {
        val tasks = mutableListOf<Task>()
        for (i in 0 .. 3 ) {

            val task = testTask.copy(
                title = "task where id: $i",
                priority = if (i == 0) -1 else 0
            )
            dao.addNewTask(task)
            tasks.add(task)
        }

        val tasksResult = dao.getAllTasksWithCommonPriority(0).first()
        assertThat(tasksResult.contains(tasks[1]) && tasks.contains(tasks[2]) && tasks.contains(tasks[3])).isTrue()
    }

    @Test
    fun success_when_read_all_completed_tests() = runTest {
        val tasks = mutableListOf<Task>()
        for (i in 0 .. 3 ) {

            val task = testTask.copy(
                title = "task where id: $i",
                completeness = if (i == 0) .05f else 0f
            )
            dao.addNewTask(task)
            tasks.add(task)
        }

        val tasksResult = dao.getAllTasksWithCommonCompleteness(0f).first()
        assertThat(tasksResult.contains(tasks[1]) && tasks.contains(tasks[2]) && tasks.contains(tasks[3])).isTrue()
    }

    //  search in title
    @Test
    fun success_when_search_in_title() = runTest {
        val tasks = mutableListOf<Task>()
        val searchKeyword = "search key"
        val wantedTask = testTask.copy(
            taskId = 7L,
            title = searchKeyword
        )

        for (i in 0 .. 3 ) {

            val task = if (i == 0) wantedTask else testTask

            dao.addNewTask(task)
            tasks.add(task)
        }

        val tasksResult = dao.searchInTaskTitle(keyWord = searchKeyword).first()
        assertThat(tasksResult.size).isEqualTo(1)
        assertThat(tasksResult[0] matches wantedTask)
    }

    @Test
    fun success_when_search_in_description() = runTest {
        val tasks = mutableListOf<Task>()
        val searchKeyword = "search key"
        val wantedTask = testTask.copy(
            taskId = 7L,
            description = searchKeyword
        )

        for (i in 0 .. 3 ) {

            val task = if (i == 0) wantedTask else testTask

            dao.addNewTask(task)
            tasks.add(task)
        }

        val tasksResult = dao.searchInTaskTitle(keyWord = searchKeyword).first()
        assertThat(tasksResult.size).isEqualTo(1)
        assertThat(tasksResult[0] matches wantedTask)
    }

}