package com.homeio.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val titulo: String,
        val mensagem: String,
        val dataCriacao: LocalDateTime,
        @Enumerated(EnumType.STRING)
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        @ManyToOne
        @JoinColumn(name = "fk_usuario_id")
        val autor: Usuario,
        @ManyToOne
        @JoinColumn(name = "fk_curso_id")
        val curso: Curso,
        @OneToMany(mappedBy = "topico")
        val resposta: List<Resposta> = listOf())