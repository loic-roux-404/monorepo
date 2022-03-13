package com.abcleaver.quizz.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.RestController

@RestController
class MainController (@Value("\${kafka.topics.product}") val topic:String,
                      @Autowired
                      private val kafkaTemplate: KafkaTemplate<String, Any>){
  @GetMapping("/")
  fun post(): ResponseEntity<Any>{
    return try {
      val message: Message<String> = MessageBuilder.withPayload("Message")
        .setHeader(KafkaHeaders.TOPIC, topic)
        .build()
      kafkaTemplate.send(message)
      return ResponseEntity.ok().build()
    } catch (e: Exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error")
    }
  }
}
