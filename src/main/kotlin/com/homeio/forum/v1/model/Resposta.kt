package com.homeio.forum.v1.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Resposta (
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id: Long,
                val mensagem: String,
                @ManyToOne
                @JoinColumn(name = "fk_topico_id")
                val topico: Topico,
                val dataCriacao: LocalDateTime,
                @ManyToOne
                @JoinColumn(name = "fk_usuario_id")
                val author: Usuario,
                val solucao: Boolean = false)