package com.chudars.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.Optional

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    val board:  Board,
    
    val name: String = "",
    val stage: Int = 0, // 0 - not started, 1 - in progress, 2 - done
    val description: String = "",
    val urgency: Int = 0, // 0 - low, 1 - medium, 2 - high
    val dueDate: String
    
)