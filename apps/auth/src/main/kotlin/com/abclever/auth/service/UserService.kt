package com.abclever.auth.service

import com.abclever.auth.model.user.User

interface UserService {
    fun save(user: User): User
    fun findAll(): List<User?>?
    fun findByEmail(email: String): User?
    fun delete(id: Long)
}
