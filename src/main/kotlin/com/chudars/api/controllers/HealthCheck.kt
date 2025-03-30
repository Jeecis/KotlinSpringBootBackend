package com.chudars.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class HelloResponse(val message: String)

@RestController
class HelloWorldJsonController {

    @GetMapping("/health")
    fun helloWorldJson(): HelloResponse {
        return HelloResponse("API is up and running!")
    }
}