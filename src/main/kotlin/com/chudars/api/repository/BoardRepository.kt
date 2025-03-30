package com.chudars.api.repository

import com.chudars.api.model.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, String> {
    fun existsByName(name: String): Boolean
}