package com.homeio.forum.v1.dto

import java.time.LocalDateTime

data class TopicoDto (var id: Long?,
                 var titulo: String?,
                 var mensagem: String?,
                 var dataCriacao: LocalDateTime?){

    constructor() : this(null, null, null, null)
}