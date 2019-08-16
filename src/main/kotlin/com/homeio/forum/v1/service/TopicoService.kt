package com.homeio.forum.v1.service

import com.homeio.forum.v1.dto.TopicoDetalheDto
import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.filter.TopicoFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TopicoService {


    fun findOneDetalhe(id: Long) : TopicoDetalheDto
    fun findOne(id: Long) : Topico
    fun save(topico: Topico) : TopicoDto
    fun update(id:Long, topico: Topico) : TopicoDto
    fun findAllFilter(filter: TopicoFilter) : List<TopicoDto>
    fun findAllFilterPageable(filter: TopicoFilter, pageable: Pageable) : Page<TopicoDto>
    fun delete(id: Long)
}