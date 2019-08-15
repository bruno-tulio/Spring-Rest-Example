package com.homeio.forum.model

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Topico(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @field:NotBlank
        val titulo: String = "",

        @field:NotBlank
        val mensagem: String = "",

        val dataCriacao: LocalDateTime = LocalDateTime.now(),

        @field:NotNull
        @Enumerated(EnumType.STRING)
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

        @ManyToOne
        @JoinColumn(name = "fk_usuario_id")
        val autor: Usuario?,

        @field:NotNull
        @ManyToOne
        @JoinColumn(name = "fk_curso_id")
        val curso: Curso?,

        @OneToMany(mappedBy = "topico")
        val resposta: List<Resposta> = listOf()){

}