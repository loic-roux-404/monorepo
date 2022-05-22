package com.abcleaver.quizz.domain

import kotlin.test.Test


internal class QuizzServiceTest {

  private var alphabet: Set<String> = setOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
    "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

  @Test
  fun quizz_should_have_the_requested_size(){
    assert(QuizzService.getQuizz(15).questions.size.equals(15))
    assert(QuizzService.getQuizz(400).questions.size.equals(400))
  }

}
