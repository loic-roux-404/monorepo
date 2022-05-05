package com.abclever.auth.controller

import com.abclever.auth.model.user.User
import com.abclever.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private val userService: UserService? = null

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun listUser(): List<User?>? {
        return userService?.findAll()
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value = "id") id: Long): String {

        userService?.delete(id)

        return "success"
    }
}
