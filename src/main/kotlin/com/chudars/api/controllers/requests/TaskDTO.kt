import com.chudars.api.model.Task

data class TaskDTO(
    val id: Long,
    val boardId: String,
    val name: String,
    val stage: Int,
    val description: String,
    val urgency: Int,
    val dueDate: String
)

// Extension function to convert Task to TaskDTO
fun Task.toDTO() = TaskDTO(
    id = this.id,
    boardId = this.board.id,
    name = this.name,
    stage = this.stage,
    description = this.description,
    urgency = this.urgency,
    dueDate = this.dueDate
)