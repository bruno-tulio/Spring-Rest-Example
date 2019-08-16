package com.homeio.forum.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class LoginDto (
        @field:Email
        @field:NotBlank
        val email: String?,
        @field:NotBlank
        val senha: String?)