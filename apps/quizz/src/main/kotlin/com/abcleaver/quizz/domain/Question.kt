import com.abcleaver.quizz.domain.alphabet.Letter
import java.util.*

data class Question(val id: UUID, val letter: Letter, val type: QuestionType) {
 fun test() {
 }
}

enum class QuestionType {
  Letter, Image
}
