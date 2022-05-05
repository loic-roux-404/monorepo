package com.abclever.auth.model.user

import org.springframework.validation.annotation.Validated
import javax.validation.constraints.*

class InteractionUserDto {

  @Validated
  class Connecting {
    @Email
    var email: String? = null

    var password: String? = null
  }

  @Validated
  class Registering : User() {
    @NotBlank
    @NotNull
    @Size(min=6, max=16)
    var confirmationPassword: String? = null
  }
}
