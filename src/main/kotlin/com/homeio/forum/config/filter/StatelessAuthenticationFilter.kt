package com.homeio.forum.config.filter

import com.homeio.forum.exception.TokenInvalidoException
import com.homeio.forum.repository.UsuarioRepository
import com.homeio.forum.security.AppUser
import com.homeio.forum.security.UserAuthentication
import com.homeio.forum.service.JWTService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.Exception
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class StatelessAuthenticationFilter(private val jwtService: JWTService,
                                    private val usuarioRepository: UsuarioRepository) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        try {
            val token = authorization(request as HttpServletRequest)
            token?.let {
                val authenticationToken = renderAuthentication(token)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
            chain.doFilter(request, response)
            SecurityContextHolder.getContext().authentication = null
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
            (response as HttpServletResponse).status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }


    fun renderAuthentication(token: String?): Authentication {
        val idUsuario = jwtService.getSuject(token).toLong()
        val usuario = usuarioRepository.getOne(idUsuario)
        return UserAuthentication(AppUser(usuario, hashSetOf()))


    }

    fun authorization(request: HttpServletRequest): String? {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        return authorization?.let {
            if (it.startsWith("Bearer ")) it.substring(7, it.length) else null
        }

    }

}