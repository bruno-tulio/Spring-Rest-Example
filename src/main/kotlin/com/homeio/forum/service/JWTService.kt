package com.homeio.forum.service

import com.homeio.forum.exception.TokenExpiredException
import com.homeio.forum.exception.TokenInvalidoException
import com.homeio.forum.model.Usuario
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*


@Service
class JWTService(
        @Value("\${jwt.key}")
        private val key: String,
        @Value("\${jwt.expirion}")
        private val expiration: Long,
        @Value("\${jwt.issuer}")
        private val issuer: String) {


    fun generateToken(usuario: Usuario, roles: List<String>): String {
        val data = Date()
        val expiration = Date(data.time + expiration)

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .setSubject("${usuario.id}")
                .setIssuer(issuer)
                .setIssuedAt(data)
                .setExpiration(expiration)
                .compact()

    }


    fun getSuject(token: String?): String {
        return try {
            val clains = Jwts.parser().setSigningKey(key).parseClaimsJws(token).body
            return claisnSuject(clains)
        } catch (e: Exception) {
            throw TokenInvalidoException("Token inválido")
        }
    }

    private fun claisnSuject(clains: Claims): String {
        return if (validatTempoExpiracao(clains)) {
            clains.subject
        } else {
            throw TokenExpiredException("Token expirado")
        }
    }

    private fun validatTempoExpiracao(clains: Claims): Boolean {
        val data = Date(System.currentTimeMillis())
        return data.before(clains.expiration)
    }
}