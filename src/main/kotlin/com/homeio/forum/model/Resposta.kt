package com.homeio.forum.model

import java.time.LocalDateTime

data class Resposta (val id: Long,
                val mensagem: String,
                val topico: Topico,
                val dataCriacao: LocalDateTime,
                val author: Usuario,
                val solucao: Boolean = false)