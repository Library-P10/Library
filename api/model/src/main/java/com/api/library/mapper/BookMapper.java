package com.api.library.mapper;

import com.api.library.dto.BookDto;
import com.api.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    List<BookDto> map(List<Book> books);

    BookDto bookToBookDto(Book book);
    Book bookDtoToBook(BookDto bookDto);

}
