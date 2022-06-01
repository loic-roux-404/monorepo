package com.abcleaver.quizz.port

import com.abcleaver.quizz.domain.Quizz

interface QuizzOut {
  fun index(size: Int): Quizz
}
