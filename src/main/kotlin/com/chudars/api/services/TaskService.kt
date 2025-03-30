package com.chudars.api.services

import TaskDTO
import com.chudars.api.controllers.requests.TaskRequest
import com.chudars.api.model.Board
import com.chudars.api.model.Task
import com.chudars.api.repository.BoardRepository
import com.chudars.api.repository.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import toDTO
import java.time.LocalDateTime
import java.util.*

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val boardRepository: BoardRepository
) {

    fun createTask(taskRequest: TaskRequest, boardID: String): Boolean{
        if (!validateBoard(boardID)){
            return false
        }

        val optionalBoard = boardRepository.findById(boardID) ?: return false

        val task = Task(
            board = optionalBoard.get(),
            name = taskRequest.name,
            stage = taskRequest.stage,
            description = taskRequest.description,
            urgency = taskRequest.urgency,
            dueDate = taskRequest.dueDate
        )

        try {
            taskRepository.save(task)
            return true
        } catch (e: Exception) {
            // Consider logging the exception here
            return false
        }

    }

    fun listTasks(boardId: String): List<TaskDTO> {
        return taskRepository.findByBoardId(boardId).map { it.toDTO() }
    }

    fun validateBoard(boardID: String): Boolean{
        return boardRepository.existsById(boardID)
    }

    fun updateTask(taskID: Long, taskRequest: TaskRequest): Boolean {

        // Find the task
        val task = taskRepository.findById(taskID).orElse(null) ?: return false

        // Create updated task
        val updatedTask = task.copy(
            name = taskRequest.name ?: task.name,
            stage = taskRequest.stage ?: task.stage,
            description = taskRequest.description ?: task.description,
            urgency = taskRequest.urgency ?: task.urgency
        )

        return try {
            taskRepository.save(updatedTask)
            true
        } catch (e: Exception) {
            false
        }
    }

    @Transactional
    fun deleteTask(id: Long): Boolean {
        return if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id)
            true
        } else {
            false
        }
    }


}