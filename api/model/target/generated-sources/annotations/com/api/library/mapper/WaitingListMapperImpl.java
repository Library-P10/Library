package com.api.library.mapper;

import com.api.library.dto.AuthorDto;
import com.api.library.dto.BookDto;
import com.api.library.dto.CategorieDto;
import com.api.library.dto.CustomerDto;
import com.api.library.dto.WaitingListDto;
import com.api.library.model.Author;
import com.api.library.model.Book;
import com.api.library.model.Categorie;
import com.api.library.model.Customer;
import com.api.library.model.WaitingList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-21T20:27:16+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class WaitingListMapperImpl implements WaitingListMapper {

    @Override
    public List<WaitingListDto> map(List<WaitingList> waitingLists) {
        if ( waitingLists == null ) {
            return null;
        }

        List<WaitingListDto> list = new ArrayList<WaitingListDto>( waitingLists.size() );
        for ( WaitingList waitingList : waitingLists ) {
            list.add( waitingListToWaitingListDto( waitingList ) );
        }

        return list;
    }

    @Override
    public WaitingListDto waitingListToWaitingListDto(WaitingList waitingList) {
        if ( waitingList == null ) {
            return null;
        }

        WaitingListDto waitingListDto = new WaitingListDto();

        waitingListDto.setId( waitingList.getId() );
        waitingListDto.setDateRequest( waitingList.getDateRequest() );
        waitingListDto.setDateSendingMail( waitingList.getDateSendingMail() );
        waitingListDto.setDateRecoveryLimit( waitingList.getDateRecoveryLimit() );
        waitingListDto.setCustomer( customerToCustomerDto( waitingList.getCustomer() ) );
        waitingListDto.setBook( bookToBookDto( waitingList.getBook() ) );

        return waitingListDto;
    }

    @Override
    public WaitingList waitingListDtoToWaitingList(WaitingListDto waitingListDto) {
        if ( waitingListDto == null ) {
            return null;
        }

        WaitingList waitingList = new WaitingList();

        waitingList.setCustomer( customerDtoToCustomer( waitingListDto.getCustomer() ) );
        waitingList.setId( waitingListDto.getId() );
        waitingList.setBook( bookDtoToBook( waitingListDto.getBook() ) );
        waitingList.setDateRequest( waitingListDto.getDateRequest() );
        waitingList.setDateSendingMail( waitingListDto.getDateSendingMail() );
        waitingList.setDateRecoveryLimit( waitingListDto.getDateRecoveryLimit() );

        return waitingList;
    }

    protected CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setAdress( customer.getAdress() );
        customerDto.setPostalCode( customer.getPostalCode() );
        customerDto.setCity( customer.getCity() );
        customerDto.setEmail( customer.getEmail() );
        customerDto.setPassword( customer.getPassword() );

        return customerDto;
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

    protected BookDto bookToBookDto(Book book) {
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

    protected Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.getId() );
        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setAdress( customerDto.getAdress() );
        customer.setPostalCode( customerDto.getPostalCode() );
        customer.setCity( customerDto.getCity() );
        customer.setEmail( customerDto.getEmail() );
        customer.setPassword( customerDto.getPassword() );

        return customer;
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

    protected Book bookDtoToBook(BookDto bookDto) {
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
}
