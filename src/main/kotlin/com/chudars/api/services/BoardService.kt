package com.chudars.api.services

import com.chudars.api.model.Board
import com.chudars.api.repository.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BoardService(private val boardRepository: BoardRepository) {

    fun createBoard(name: String): Board {
        val board = boardRepository.save(
            Board(
            id = generateUniqueBoardName(),
            name = name,
            ))
        return board
    }

    @Transactional
    fun deleteBoard(id: String): Boolean {
        return if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    fun listBoards(): List<Board> {
        return boardRepository.findAll()
    }

    fun getBoardById(id: String): Optional<Board> {
        return boardRepository.findById(id)
    }
    
    fun generateUniqueBoardName(): String {
        var uniqueName = uniqueString()

        while (boardRepository.existsByName(uniqueName)) {
            uniqueName = uniqueString()
        }
        
        return uniqueName
    }

    fun uniqueString(): String{
        val baseName = (1..6)
            .map {
                val charPool : List<Char> = ('A'..'Z') + ('0'..'9')
                charPool.random()
            }
            .joinToString("")

        return baseName
    }
}