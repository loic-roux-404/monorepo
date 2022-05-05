package com.abclever.auth

import com.abclever.auth.service.impl.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
// import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@ComponentScan(basePackages = ["com.abclever" ])
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

  @Autowired
  private val userDetailsService: UserServiceImpl? = null

  @Bean
  fun PasswordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  fun authProvider(): DaoAuthenticationProvider? {
    val authProvider = DaoAuthenticationProvider()
    authProvider.setUserDetailsService(userDetailsService)
    authProvider.setPasswordEncoder(PasswordEncoder())
    return authProvider
  }

  @Throws(Exception::class)
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.authenticationProvider(authProvider());
  }

  @Throws(java.lang.Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .httpBasic().disable()
//      .cors()
//      .and()
//      .authorizeRequests()
//      .antMatchers(HttpMethod.GET, "/auth/users")
//      .hasAuthority("SCOPE_read")
//      .anyRequest()
//      .authenticated()
    }
}
