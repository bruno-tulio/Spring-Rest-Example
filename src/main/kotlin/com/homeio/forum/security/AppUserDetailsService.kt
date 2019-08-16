package com.homeio.forum.security

import com.homeio.forum.model.Usuario
import com.homeio.forum.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Qualifier("appUserDetailService")
class AppUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    override fun loadUserByUsername(email: String): UserDetails {
        val usuario = usuarioRepository.findByEmailAndAtivoTrue(email).orElseThrow {
            UsernameNotFoundException("Usuário e/ou senha incorretos")
        }

        return AppUser(usuario, getAuthorities(usuario))
    }

    private fun getAuthorities(usuario: Usuario): Set<SimpleGrantedAuthority> {
        return usuarioRepository.getPermissoes(usuario.id).map {
            SimpleGrantedAuthority(it.toUpperCase())
        }.toSet()

    }

}