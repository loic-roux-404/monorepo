package com.abcleaver.quizz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuizzApplication

fun main(args: Array<String>) {
	runApplication<QuizzApplication>(*args)
}
