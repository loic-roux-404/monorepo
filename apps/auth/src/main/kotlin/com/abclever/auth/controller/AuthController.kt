package com.abclever.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class AuthController {
  @GetMapping("/")
  fun index(): String {
    return "blog"
  }
}
