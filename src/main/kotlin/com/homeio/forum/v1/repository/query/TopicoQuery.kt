package com.homeio.forum.v1.repository.query

import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.filter.TopicoFilter

interface TopicoQuery {

    fun findAllFilter(topicoFilter: TopicoFilter): List<Topico>
}