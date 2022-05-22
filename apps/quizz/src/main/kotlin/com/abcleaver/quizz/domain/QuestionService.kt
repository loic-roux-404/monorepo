package com.abcleaver.quizz.domain

internal object QuestionService {

  fun createQuestion(letter: Letter): Question {
    return Question(letter.copy(), QuestionType.getRandom())
  }

}
