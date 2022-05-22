package com.abcleaver.quizz.domain

import com.abcleaver.quizz.domain.alphabet.Letter
import kotlin.random.Random

enum class QuestionType {
  Letter, Image;

  companion object {
    fun getRandom(): QuestionType {
      val questionTypes = QuestionType.values()

      return questionTypes.get(Random.nextInt(0, questionTypes.size))
    }

  }

}

// TODO add image later
//data class Question(val letter: Letter, val image: Image, val type: QuestionType)
data class Question(val letter: Letter, val type: QuestionType)
