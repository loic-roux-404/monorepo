package com.abclever.auth.service.impl

import org.springframework.security.core.userdetails.UserDetailsService
import com.abclever.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import com.abclever.auth.model.user.UserDao
import com.abclever.auth.model.user.User
import kotlin.Throws
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.User as SpringSecurityUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service(value = "userService")
class UserServiceImpl : UserDetailsService, UserService {
  @Autowired
  private val userDao: UserDao? = null

  private val authority: List<SimpleGrantedAuthority>
    get() = listOf(SimpleGrantedAuthority("ROLE_USER"))

  @Throws(UsernameNotFoundException::class)
  override fun loadUserByUsername(email: String): UserDetails {
    val user: User = userDao!!.findByEmail(email)
      ?: throw UsernameNotFoundException("Invalid username or password.")
    return SpringSecurityUser(user.email, user.password, authority)
  }

  override fun findAll(): MutableList<User> {
    val list: MutableList<User> = mutableListOf()
    userDao!!.findAll().iterator().forEachRemaining { if (it != null) list.add(it) }
    return list
  }

  override fun findByEmail(email: String): User? {
    return userDao!!.findByEmail(email)
  }

  override fun delete(id: Long) {
    userDao!!.deleteById(id)
  }

  override fun save(user: User): User {
    return userDao!!.save(user)
  }
}
