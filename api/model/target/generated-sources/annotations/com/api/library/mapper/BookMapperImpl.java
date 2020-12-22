package com.api.library.mapper;

import com.api.library.dto.AuthorDto;
import com.api.library.dto.BookDto;
import com.api.library.dto.CategorieDto;
import com.api.library.model.Author;
import com.api.library.model.Book;
import com.api.library.model.Categorie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:30:28+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 11.0.6 (Ubuntu)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public List<BookDto> map(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( books.size() );
        for ( Book book : books ) {
            list.add( bookToBookDto( book ) );
        }

        return list;
    }

    @Override
    public BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setTitle( book.getTitle() );
        bookDto.setPubDate( book.getPubDate() );
        bookDto.setPage( book.getPage() );
        bookDto.setSynopsis( book.getSynopsis() );
        bookDto.setCover( book.getCover() );
        bookDto.setCategorie( categorieToCategorieDto( book.getCategorie() ) );
        bookDto.setAuthor( authorToAuthorDto( book.getAuthor() ) );

        return bookDto;
    }

    @Override
    public Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthor( authorDtoToAuthor( bookDto.getAuthor() ) );
        book.setCategorie( categorieDtoToCategorie( bookDto.getCategorie() ) );
        book.setId( bookDto.getId() );
        book.setTitle( bookDto.getTitle() );
        book.setPubDate( bookDto.getPubDate() );
        book.setPage( bookDto.getPage() );
        book.setSynopsis( bookDto.getSynopsis() );
        book.setCover( bookDto.getCover() );

        return book;
    }

    protected CategorieDto categorieToCategorieDto(Categorie categorie) {
        if ( categorie == null ) {
            return null;
        }

        CategorieDto categorieDto = new CategorieDto();

        categorieDto.setId( categorie.getId() );
        categorieDto.setLabel( categorie.getLabel() );

        return categorieDto;
    }

    protected AuthorDto authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( author.getId() );
        authorDto.setFirstName( author.getFirstName() );
        authorDto.setLastName( author.getLastName() );

        return authorDto;
    }

    protected Author authorDtoToAuthor(AuthorDto authorDto) {
        if ( authorDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDto.getId() );
        author.setFirstName( authorDto.getFirstName() );
        author.setLastName( authorDto.getLastName() );

        return author;
    }

    protected Categorie categorieDtoToCategorie(CategorieDto categorieDto) {
        if ( categorieDto == null ) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setId( categorieDto.getId() );
        categorie.setLabel( categorieDto.getLabel() );

        return categorie;
    }
}
