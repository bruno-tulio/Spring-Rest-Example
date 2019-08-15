package com.homeio.forum.resources.v1

import com.homeio.forum.dto.TopicoDto
import com.homeio.forum.mapper.TopicoMapper
import com.homeio.forum.model.Topico
import com.homeio.forum.repository.TopicoRepository
import com.homeio.forum.repository.excption.EntityNotFoundException
import com.homeio.forum.repository.filter.TopicoFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("${VersionV1Resources.VERSION}/topicos")
class TopicsResources {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @GetMapping
    fun get(topicoFilter: TopicoFilter): ResponseEntity<List<TopicoDto>?> {
        return ResponseEntity.ok(topicoRepository.findAllFilter(topicoFilter).let { topicos -> topicos?.map { TopicoMapper.INSTANCE.topicoToTopicoDTO(it) } ?: listOf() })
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Topico> {
        return ResponseEntity.ok(topicoRepository.findById(id).orElseThrow {  EntityNotFoundException("Topico não existe") })
    }

    @PostMapping
    fun salvar(@RequestBody @Valid topico: Topico, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        val topico  = topicoRepository.save(topico)
        val uri = uriComponentsBuilder.path("topicos/{codigo}").buildAndExpand(topico.id).toUri()
        return ResponseEntity.created(uri).body(TopicoMapper.INSTANCE.topicoToTopicoDTO(topico))

    }

    @PutMapping("/{id}")
    fun update(@PathVariable id :Long, @RequestBody @Valid topico: Topico): ResponseEntity<Any> {

        val topicoExistente = topicoRepository.findById(id).orElseThrow {  EntityNotFoundException("Topico não existe") }
        val topicoUpdate = topico.copy(id = topicoExistente.id)

        topicoRepository.save(topicoUpdate)
        return ResponseEntity.noContent().build()
    }


}