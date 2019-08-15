package com.homeio.forum.v1.resources

import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.mapper.TopicoMapper
import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.TopicoRepository
import com.homeio.forum.v1.repository.excption.EntityNotFoundException
import com.homeio.forum.v1.repository.filter.TopicoFilter
import com.homeio.forum.v1.VersionV1Resources
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
    fun get(topicoFilter: TopicoFilter): ResponseEntity<Any> {
        return topicoRepository
                .findAllFilter(topicoFilter).let { topicos -> ResponseEntity.ok(TopicoMapper.INSTANCE.mapToTopicosDto(topicos)) }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Any> {
        return topicoRepository.findById(id).orElseThrow { EntityNotFoundException("Topico não existe") }.let {
            ResponseEntity.ok(TopicoMapper.INSTANCE.topicoToTopicoDetalheDTO(it))
        }

    }

    @PostMapping
    fun salvar(@RequestBody @Valid topico: Topico, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        val topico = topicoRepository.save(topico)
        val uri = uriComponentsBuilder.path("topicos/{codigo}").buildAndExpand(topico.id).toUri()
        return ResponseEntity.created(uri).body(TopicoMapper.INSTANCE.mapToTopicoDto(topico))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid topico: Topico): ResponseEntity<Any> {
        val topicoExistente = topicoRepository.findById(id).orElseThrow { EntityNotFoundException("Topico não existe") }
        val topicoUpdate = topico.copy(id = topicoExistente.id)
        topicoRepository.save(topicoUpdate)
        return ResponseEntity.noContent().build()
    }


}