package com.abcleaver.quizz.domain

import com.abcleaver.quizz.domain.alphabet.AlphabetService

object QuizzService {

  fun getQuizz(questionNumber: Int = 15): Quizz {
    val questions = (1..questionNumber)
      .map { AlphabetService.getRandomLetter() }
      .map { QuestionService.createQuestion(it)  }
      .toList()

    return Quizz(questions);
  }

}
