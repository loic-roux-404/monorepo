package com.abcleaver.quizz.domain.alphabet

import java.util.*

internal data class Alphabet(val letter: Set<Letter>) {

  companion object {
    fun from(lettersString: Set<String>): Alphabet {
      val letters = lettersString.map { stringToChars(it) }
        .map { Letter(it) }
        .toSet()

      return Alphabet(letters)
    }

    private fun stringToChars(it: String): List<Char> {
      return Optional.ofNullable(it.chars()
        .mapToObj { it.toChar() }
        .toList())
        .orElse(listOf())
    }
  }
}




