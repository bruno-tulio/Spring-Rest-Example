package com.homeio.forum.v1.repository.query

import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.filter.TopicoFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TopicoQuery {

    fun findAllFilter(topicoFilter: TopicoFilter): List<Topico>

    fun findAllFilterPageable(topicoFilter: TopicoFilter, pageable: Pageable) : Page<TopicoDto>
}