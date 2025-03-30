package com.chudars.api.model

import jakarta.persistence.*

@Entity
@Table(name = "Boards")
data class Board(
    @Id
    val id: String = "",
    
    val name: String = "",
)