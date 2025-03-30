package com.chudars.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class ApiApplication {
	@Bean
	fun objectMapper(): ObjectMapper {
		val mapper = ObjectMapper()
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
		return mapper
	}
}


fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
