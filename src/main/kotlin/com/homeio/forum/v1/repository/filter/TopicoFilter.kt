package com.homeio.forum.v1.repository.filter

import java.time.LocalDate

class TopicoFilter(val titulo: String?,
                   val mensagem: String?,
                   val dataCriacao: LocalDate?)
