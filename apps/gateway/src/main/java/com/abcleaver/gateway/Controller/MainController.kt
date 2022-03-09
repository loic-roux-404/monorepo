import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
public class MainController {

  @GetMapping("/")
  fun post(): ResponseEntity<Any> {
    return ResponseEntity.ok().build()
  }

}
