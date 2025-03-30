package com.chudars.api.controllers

import TaskDTO
import com.chudars.api.controllers.requests.CreateBoardRequest
import com.chudars.api.controllers.requests.TaskRequest
import com.chudars.api.model.Board
import com.chudars.api.model.Task
import com.chudars.api.services.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
class TaskController(private val taskService: TaskService) {

    @PostMapping("/board/{id}/tasks")
    fun createTask(@RequestBody taskRequest: TaskRequest, @PathVariable id: String): ResponseEntity<Void> {
        try {
            val isCreated = taskService.createTask(taskRequest, id)
            if (isCreated) {
               return ResponseEntity.noContent().build()
            } else {
                return ResponseEntity.badRequest().build()
            }
        } catch (e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @GetMapping("/board/{id}/tasks")
    fun listTasks(@PathVariable id: String): ResponseEntity<List<TaskDTO>> {
        return try {
            val tasks = taskService.listTasks(id)
            ResponseEntity.ok(tasks)
        } catch (e: Exception) {
            throw Exception("Failed to connect to database: ${e.message}")
        }
    }

    @PutMapping("/board/{boardID}/tasks/{taskID}")
    fun updateTask(@RequestBody taskRequest: TaskRequest, @PathVariable boardID: String, @PathVariable taskID: Long):  ResponseEntity<Void> {
        try {
            val isUpdated = taskService.updateTask(taskID, taskRequest)
            if (isUpdated){
                return ResponseEntity.noContent().build()
            }else{
                return ResponseEntity.internalServerError().build()
            }
        } catch (e: Exception) {
            throw Exception("Failed to connect to database: ${e.message}")
        }
    }

    @DeleteMapping("/board/{boardID}/tasks/{taskID}")
    fun deleteBoard(@PathVariable boardID: String, @PathVariable taskID: Long): ResponseEntity<Void> {
         try {
            if (taskService.deleteTask(taskID)) {
                return ResponseEntity.noContent().build()
            } else {
                throw Exception("Board with ID: $taskID not found")
            }
        } catch (e: Exception) {
            throw Exception("Failed to delete board: ${e.message}")
        }
    }
}