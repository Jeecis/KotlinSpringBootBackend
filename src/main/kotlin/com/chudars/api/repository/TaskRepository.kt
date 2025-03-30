package com.chudars.api.repository

import com.chudars.api.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findByBoardId(boardId: String): List<Task>
}