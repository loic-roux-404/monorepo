package com.abclever.auth.controller

import com.abclever.auth.model.user.InteractionUserDto
import com.abclever.auth.model.user.User
import com.abclever.auth.service.UserService
import com.sun.jdi.request.DuplicateRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.NoSuchElementException

@RestController
@RequestMapping("/")
class AuthController {

  @Autowired
  private val userService: UserService? = null

  @ResponseBody
  @PostMapping("/login")
  fun login(@RequestBody connecting: InteractionUserDto.Connecting): User? {
    return userService!!.findByEmail(connecting.email!!) ?:
      throw NoSuchElementException(String.format("User %s not found", connecting.email))
  }

  @PostMapping(value = ["/register"])
  fun create(@RequestBody user: InteractionUserDto.Registering): User? {
    if (user.password != user.confirmationPassword) {
      throw DuplicateRequestException("passwords aren't the same")
    }

    return userService?.save(user)
  }
}
