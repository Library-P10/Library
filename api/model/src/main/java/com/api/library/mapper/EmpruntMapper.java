package com.api.library.mapper;

import com.api.library.dto.EmpruntDto;
import com.api.library.model.Emprunt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmpruntMapper {

    EmpruntMapper INSTANCE = Mappers.getMapper(EmpruntMapper.class);

    List<EmpruntDto> map(List<Emprunt> emprunts);
    EmpruntDto empruntToEmpruntDto( Emprunt emprunt);
    Emprunt empruntDtoToEmprunt ( EmpruntDto empruntDto);
}
