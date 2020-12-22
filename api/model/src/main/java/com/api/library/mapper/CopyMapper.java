package com.api.library.mapper;

import com.api.library.dto.BookDto;
import com.api.library.dto.CopyDto;
import com.api.library.model.Book;
import com.api.library.model.Copy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CopyMapper {

    List<CopyDto> map(List<Copy> copies);

    CopyMapper INSTANCE = Mappers.getMapper(CopyMapper.class);
    CopyDto copyToCopyDto( Copy copy);
    Copy copyDtoToCopy ( CopyDto copyDto);
}
