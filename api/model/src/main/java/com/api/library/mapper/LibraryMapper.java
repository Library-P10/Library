package com.api.library.mapper;

import com.api.library.dto.LibraryDto;
import com.api.library.model.Library;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LibraryMapper {

    LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);
    LibraryDto libraryToLibraryDto (Library library);
}
