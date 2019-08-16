package com.homeio.forum.model

import javax.persistence.*

@Entity
data class Usuario(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val nome: String,
        val email: String,
        val senha: String,
        val ativo: Boolean,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "usuario_perfil", joinColumns = [JoinColumn(name = "fk_usuario_id")],
                inverseJoinColumns = [JoinColumn(name = "fk_perfil_id")])
        val perfis: List<Perfil>)