package com.homeio.forum.v1.service.impl

import com.homeio.forum.v1.dto.TopicoDetalheDto
import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.mapper.TopicoMapper
import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.TopicoRepository
import com.homeio.forum.v1.repository.excption.EntityNotFoundException
import com.homeio.forum.v1.repository.filter.TopicoFilter
import com.homeio.forum.v1.service.TopicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoServiceImpl : TopicoService{



    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    override fun findOne(id: Long): Topico {
        return topicoRepository.findById(id).orElseThrow{ EntityNotFoundException("Topico não existe") }
    }

    override fun findOneDetalhe(id: Long): TopicoDetalheDto {
        return TopicoMapper.INSTANCE.topicoToTopicoDetalheDTO(findOne(id))
    }

    override fun save(topico: Topico): TopicoDto {
       return TopicoMapper.INSTANCE.mapToTopicoDto(topicoRepository.save(topico))
    }

    override fun update(id: Long, topico: Topico): TopicoDto {
        findOne(id)
        return TopicoMapper.INSTANCE.mapToTopicoDto(topicoRepository.save(topico.copy(id=id)))
    }

    override fun findAllFilter(filter: TopicoFilter): List<TopicoDto> {
       return TopicoMapper.INSTANCE.mapToTopicosDto(topicoRepository.findAllFilter(filter))
    }

    override fun findAllFilterPageable(filter: TopicoFilter, pageable: Pageable): Page<TopicoDto> {
       return topicoRepository.findAllFilterPageable(filter, pageable)
    }

    override fun delete(id: Long) {
        val topicoExistente = findOne(id)
        topicoRepository.delete(topicoExistente)
    }


}