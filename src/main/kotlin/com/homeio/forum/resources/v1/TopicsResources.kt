package com.homeio.forum.resources.v1

import com.homeio.forum.dto.TopicoDto
import com.homeio.forum.mapper.TopicoMapper
import com.homeio.forum.model.Curso
import com.homeio.forum.model.StatusTopico
import com.homeio.forum.model.Topico
import com.homeio.forum.model.Usuario
import com.homeio.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("${VersionV1Resources.VERSION}/topics")
class TopicsResources{



    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @GetMapping
    fun getTopics(): List<TopicoDto> {

        return topicoRepository.findAll().map {  TopicoMapper.INSTANCE.topicoToTopicoDTO(it) }
    }
}