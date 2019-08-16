package com.homeio.forum.model

import javax.persistence.*

@Entity
class Perfil(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val nome: String,

        @ManyToMany
        @JoinTable(name = "perfil_permissao", joinColumns = [JoinColumn(name = "fk_perfil_id")],
                inverseJoinColumns = [JoinColumn(name = "fk_permissao_id")])
        val permissoes : List<Permissao>

)