package com.homeio.forum.repository

import com.homeio.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, Long> {

    fun findByEmailAndAtivoTrue(email: String): Optional<Usuario>

    @Query("select distinct perm.nome from Usuario u inner join u.perfis perf inner join perf.permissoes perm where u.id = :id")
    fun getPermissoes(@Param("id") id: Long): List<String>


}