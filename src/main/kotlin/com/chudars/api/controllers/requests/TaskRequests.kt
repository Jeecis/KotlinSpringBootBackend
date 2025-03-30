package com.chudars.api.controllers.requests

data class TaskRequest(
    val name: String = "",
    val stage: Int = 0,
    val description: String = "",
    val urgency: Int = 0,
    val dueDate: String = ""
)

