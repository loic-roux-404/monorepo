package com.abcleaver.quizz.domain

import com.abcleaver.quizz.domain.alphabet.Letter

object QuestionService {

  fun createQuestion(letter: Letter): Question {
    return Question(letter.copy(), QuestionType.getRandom())
  }

}
