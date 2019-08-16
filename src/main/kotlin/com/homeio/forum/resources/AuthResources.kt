package com.homeio.forum.resources

import com.homeio.forum.dto.LoginDto
import com.homeio.forum.dto.TokenDto
import com.homeio.forum.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthResources {

    @Autowired
    private lateinit var authService: AuthService

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun auth(@Valid @ModelAttribute loginDto: LoginDto): ResponseEntity<Any> {
        return ResponseEntity.ok(authService.authenticate(loginDto))

    }


}