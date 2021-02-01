package com.api.library.service.impl;

import com.api.library.dto.EmpruntDto;
import com.api.library.model.*;
import com.api.library.repository.CopyRepository;
import com.api.library.repository.CustomerRepository;
import com.api.library.repository.EmpruntRepository;
import com.api.library.service.exception.EmpruntNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpruntServiceImplTest {

    @Mock EmpruntRepository empruntRepository;
    @Mock CopyRepository copyRepository;
    @Mock CustomerRepository customerRepository;

    @InjectMocks
    private EmpruntServiceImpl empruntServiceUnderTest;

    private Book createBook() {
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("FirstName");
        author.setLastName("LastName");

        Categorie categorie = new Categorie();
        categorie.setId(2L);
        categorie.setLabel("Police");

        Book book = new Book();
        book.setId(3L);
        book.setAuthor(author);
        book.setCategorie(categorie);
        book.setPage(100);
        book.setCover(null);
        book.setPubDate(new Date());
        book.setTitle("TestTitle");
        book.setSynopsis("TestSynopsis");

        return book;
    }
    private Copy createCopy() {
        Library library = new Library();
        library.setCopyList(null);
        library.setAdress("Test adresse");
        library.setEmail("test Email");
        library.setPhoneNum("0000");
        library.setId(5L);

        Copy copy = new Copy();
        copy.setBook(createBook());
        copy.setId(1L);
        copy.setLibrary(library);
        copy.setStatus("Disponible");
        copy.setFormat("Poche");

        return copy;
    }
    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setAdress("Adresse de test");
        customer.setEmail("Email de test");
        customer.setFirstName("First Name");
        customer.setLastName("Last Name");
        customer.setCity("City de test");
        customer.setPostalCode("00000");
        customer.setPassword("test");

        return customer;
    }
    private ArgumentCaptor<Emprunt> argumentCaptor = ArgumentCaptor.forClass(Emprunt.class);
    
    @Test
    void getEmpruntByIdCustomer() {
        List<Emprunt> emprunts = new ArrayList<>();
        Emprunt emprunt = new Emprunt();
        emprunt.setId(2L);
        emprunts.add(emprunt);
        when(empruntRepository.getEmpruntByIdCustomer(1L)).thenReturn(emprunts);
        List<EmpruntDto> empruntDtos = empruntServiceUnderTest.getEmpruntByIdCustomer(1L);
        assertThat(empruntDtos.get(0).getId()).isEqualTo(emprunts.get(0).getId());
    }

    @Test
    void addEmprunt() {
        Copy copy = new Copy();
        copy = createCopy();
        Emprunt emprunt = new Emprunt();
        emprunt.setId(2L);
        emprunt.setEmpruntDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(emprunt.getEmpruntDate());
        calendar.add(Calendar.DATE, 28);
        emprunt.setReturnDate(calendar.getTime());
        emprunt.setExtended(false);
        emprunt.setCopy(copy);
        emprunt.setCustomer(createCustomer());

        when(copyRepository.findFirstByFormatAndLibrary_Nom(
                emprunt.getCopy().getFormat(),
                emprunt.getCopy().getLibrary().getNom(),
                emprunt.getCopy().getBook().getId())).thenReturn(emprunt.getCopy());

        when(customerRepository.findCustomerById(2L)).thenReturn(emprunt.getCustomer());

        copy.setStatus("Indisponible");

        empruntServiceUnderTest.addEmprunt(emprunt.getCopy().getBook().getId(),
                emprunt.getCopy().getFormat(),
                emprunt.getCopy().getLibrary().getNom(),
                emprunt.getCustomer().getId());
        verify(empruntRepository,times(1)).save(argumentCaptor.capture());
    }

    @Test
    void deleteEmprunt() {
    }

    @Test
    void returnEmprunt() {
    }

    @Test
    void deleteEmpruntByIdCopy() {
    }

    @DisplayName("ExtendLoan")
    @Test
    void whenExtendIsTrue_shouldThrowEmpruntNotFoundException() {
        Emprunt emprunt = new Emprunt();
        emprunt.setId(1L);
        emprunt.setExtended(true);
        when(empruntRepository.getEmpruntById(1L)).thenReturn(emprunt);

        assertThatThrownBy(()-> empruntServiceUnderTest.extendLoan(1L))
                .isInstanceOf(EmpruntNotFoundException.class)
                .hasMessage("Emprunt not found");
    }

    @DisplayName("ExtendLoan")
    @Test
    void whenExtendIsFalse_shouldSaveCalled() {
        Emprunt emprunt = new Emprunt();
        emprunt.setId(1L);
        emprunt.setExtended(false);
        emprunt.setEmpruntDate(new Date());
        emprunt.setReturnDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(emprunt.getEmpruntDate());
        calendar.add(Calendar.DATE, 28);
        when(empruntRepository.getEmpruntById(1L)).thenReturn(emprunt);


        empruntServiceUnderTest.extendLoan(1L);
        verify(empruntRepository,times(1)).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getReturnDate()).isEqualTo(calendar.getTime());
    }

    @Test
    void getEmpruntExpiredLoanDate() {
    }

    @Test
    void getNextReturn() {
    }
}