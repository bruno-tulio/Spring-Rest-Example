package com.homeio.forum.security

import com.homeio.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AppUser(val usuario: Usuario, authorities: Set<GrantedAuthority>?) :
        User(usuario.email, usuario.senha, authorities) {

}