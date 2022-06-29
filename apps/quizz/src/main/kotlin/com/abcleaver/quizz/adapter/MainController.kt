package com.abcleaver.quizz.adapter

import com.abcleaver.quizz.domain.Quizz
import com.abcleaver.quizz.domain.QuizzService
import com.abcleaver.quizz.port.QuizzOut
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController : QuizzOut {
  @GetMapping("/quizz")
  override fun index(@RequestParam("size") size: Int): Quizz {
//    TODO Convert domain model to dto
    return QuizzService.get(size)
  }
}
