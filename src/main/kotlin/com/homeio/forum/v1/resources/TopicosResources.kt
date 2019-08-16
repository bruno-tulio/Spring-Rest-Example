package com.homeio.forum.v1.resources

import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.mapper.TopicoMapper
import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.TopicoRepository
import com.homeio.forum.v1.repository.excption.EntityNotFoundException
import com.homeio.forum.v1.repository.filter.TopicoFilter
import com.homeio.forum.v1.VersionV1Resources
import com.homeio.forum.v1.service.TopicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("${VersionV1Resources.VERSION}/topicos")
class TopicosResources {

    @Autowired
    private lateinit var topicoService: TopicoService

    @GetMapping
    fun get(topicoFilter: TopicoFilter, @PageableDefault(size = 10) pageable: Pageable): ResponseEntity<Any> {
        return ResponseEntity.ok(topicoService.findAllFilterPageable(topicoFilter, pageable))
    }

    @Cacheable(value = "topicoPorId", key = "#id")
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(topicoService.findOneDetalhe(id))

    }

    @PostMapping
    fun salvar(@RequestBody @Valid topico: Topico, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        val topicoDto = topicoService.save(topico)
        val uri = uriComponentsBuilder.path("topicos/{codigo}").buildAndExpand(topicoDto.id).toUri()
        return ResponseEntity.created(uri).body(topicoDto)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid topico: Topico): ResponseEntity<Any> {
        topicoService.update(id, topico)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any>{
        topicoService.delete(id)
        return ResponseEntity.noContent().build()
    }


}