package com.homeio.forum.v1.mapper;

import com.homeio.forum.v1.dto.TopicoDto;
import com.homeio.forum.v1.model.Topico;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-14T16:11:36-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
public class TopicoMapperImpl implements TopicoMapper {

    @Override
    public TopicoDto topicoToTopicoDTO(Topico topico) {
        if ( topico == null ) {
            return null;
        }

        TopicoDto topicoDto = new TopicoDto();

        topicoDto.setTitulo( topico.getTitulo() );
        topicoDto.setId( topico.getId() );
        topicoDto.setDataCriacao( topico.getDataCriacao() );
        topicoDto.setMensagem( topico.getMensagem() );

        return topicoDto;
    }
}
