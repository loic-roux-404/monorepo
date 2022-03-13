package com.abcleaver.quizz.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class User(val name: String, val firtname: String)

// Dans la doc a supprimer apres
@RestController
class MainController (@Value("\${kafka.topics.product}") val topic:String,
                      @Autowired private val kafkaTemplate: KafkaTemplate<String, Any>,
                      @Autowired private val objectMapper: ObjectMapper){
  @GetMapping("/produce_message")
  fun produceMessage(): ResponseEntity<Any>{
    try {
      val user = User("jack", "henri")
      val message: Message<String> = MessageBuilder.withPayload(objectMapper.writeValueAsString(user))
        .setHeader(KafkaHeaders.TOPIC, topic)
        .build()
      kafkaTemplate.send(message)
      return ResponseEntity.ok().build()
    } catch (e: Exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
    }
  }
}
