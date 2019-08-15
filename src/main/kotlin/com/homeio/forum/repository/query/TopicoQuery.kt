package com.homeio.forum.repository.query

import com.homeio.forum.model.Topico
import com.homeio.forum.repository.filter.TopicoFilter

interface TopicoQuery {

    fun findAllFilter(topicoFilter: TopicoFilter): List<Topico>?
}