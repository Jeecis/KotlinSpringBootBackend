package com.chudars.api.controllers

import com.chudars.api.controllers.requests.CreateBoardRequest
import com.chudars.api.model.Board
import com.chudars.api.services.BoardService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
class BoardController(private val boardService: BoardService) {

    @PostMapping("/board")
    fun createBoard(@RequestBody boardRequest: CreateBoardRequest): Board {
        try {
            val board = boardService.createBoard(boardRequest.name)
            return board
        } catch (e: Exception) {
            throw Exception("Failed to connect to database: ${e.message}")
        }
    }

    @GetMapping("/boards")
    fun listBoards(): List<Board> {
        return try {
            boardService.listBoards()
        } catch (e: Exception) {
            throw Exception("Failed to connect to database: ${e.message}")
        }
    }

    @GetMapping("/board/{id}")
    fun getBoardById(@PathVariable id: String):  Optional<Board> {
        return try {
            boardService.getBoardById(id)
        } catch (e: Exception) {
            throw Exception("Failed to connect to database: ${e.message}")
        }
    }

    @DeleteMapping("/board/{id}")
    fun deleteBoard(@PathVariable id: String): String {
        return try {
            if (boardService.deleteBoard(id)) {
                "Successfully deleted board with ID: $id"
            } else {
                throw Exception("Board with ID: $id not found")
            }
        } catch (e: Exception) {
            throw Exception("Failed to delete board: ${e.message}")
        }
    }
}