package com.abclever.auth.model.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : CrudRepository<User?, Long?> {
    fun findByEmail(email: String?): User?
}
