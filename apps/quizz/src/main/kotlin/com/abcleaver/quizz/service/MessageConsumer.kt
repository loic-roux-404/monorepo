package com.abcleaver.quizz.service

import com.abcleaver.quizz.controller.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

// Dans la doc a supprimer
@Service
class MessageConsumer (@Autowired private val objectMapper: ObjectMapper) {

  @KafkaListener(topics = ["product"], groupId = "products")
  fun consume(message: String) {
    val user = objectMapper.readValue(message, User::class.java)
    println(user.name)
  }

}
