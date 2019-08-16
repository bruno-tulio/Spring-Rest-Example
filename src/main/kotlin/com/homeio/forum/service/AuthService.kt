package com.homeio.forum.service

import com.homeio.forum.dto.LoginDto
import com.homeio.forum.dto.TokenDto
import com.homeio.forum.security.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService{

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtService: JWTService

    fun authenticate(loginDto: LoginDto): TokenDto {
        try {
            val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.senha)
            val authResult =  this.authenticationManager.authenticate(authenticationToken)
            val appUser = authResult.principal as AppUser
            val token = jwtService.generateToken(appUser.usuario, appUser.authorities.map { it.authority })
            return TokenDto(token)
        }catch (e: Exception){
            throw UsernameNotFoundException(e.message)
        }
    }


}