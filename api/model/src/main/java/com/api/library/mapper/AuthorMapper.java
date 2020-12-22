package com.api.library.mapper;

import com.api.library.dto.AuthorDto;
import com.api.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    AuthorDto authorToAuthorDto(Author author);
    Author authorDtoToAuthor (AuthorDto authorDto);
}
