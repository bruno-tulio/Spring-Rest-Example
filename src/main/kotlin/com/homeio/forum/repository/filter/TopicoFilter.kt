package com.homeio.forum.repository.filter

import java.time.LocalDate

class TopicoFilter(val titulo: String?,
                   val mensagem: String?,
                   val dataCriacao: LocalDate?)
