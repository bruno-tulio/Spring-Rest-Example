package com.homeio.forum.v1.mapper

import com.homeio.forum.v1.dto.TopicoDetalheDto
import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.model.Topico
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers


@Mapper
interface TopicoMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(TopicoMapper::class.java)
    }

    fun mapToTopicoDto(topico: Topico): TopicoDto

    fun mapToTopicosDto(topicos: List<Topico>): List<TopicoDto>

    @Mappings(
        Mapping(target = "autor", source = "autorDescricao")
    )
    fun topicoToTopicoDetalheDTO(topico: Topico): TopicoDetalheDto


    /*@InheritInverseConfiguration
        fun topicoDetalheDtoToTopico(topicoDetalheDto: TopicoDetalheDto): Topico
    */
    /* @InheritInverseConfiguration
        fun topicoDtoToTopico(topicoDto: TopicoDto) : Topico*/

}