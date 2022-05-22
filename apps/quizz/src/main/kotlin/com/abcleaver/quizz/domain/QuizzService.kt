package com.abcleaver.quizz.domain

import com.abcleaver.quizz.port.QuizzServiceOut

object QuizzService : QuizzServiceOut {

  fun get (questionNumber: Int = 15): Quizz {
    val questions = (1..questionNumber)
      .map { AlphabetService.getRandomLetter() }
      .map { QuestionService.createQuestion(it)  }
      .toList()

    return Quizz(questions);
  }

}
