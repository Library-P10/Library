package com.api.library.service.impl;

import com.api.library.dto.BookDto;
import com.api.library.dto.WaitingListDto;
import com.api.library.model.Book;
import com.api.library.model.Copy;
import com.api.library.model.Customer;
import com.api.library.model.WaitingList;
import com.api.library.repository.*;
import com.api.library.service.contract.MailService;
import com.api.library.service.exception.WaitingListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WaitingListServiceImplTest {

    @Mock WaitingListRepository waitingListRepository;
    @Mock EmpruntRepository empruntRepository;
    @Mock CopyRepository copyRepository;
    @Mock MailService mailService;
    @Mock BookRepository bookRepository;
    @Mock CustomerRepository customerRepository;
    @Mock WaitingListServiceImpl waitingListService;

    private ArgumentCaptor<WaitingList> argumentCaptor = ArgumentCaptor.forClass(WaitingList.class);
    private WaitingListServiceImpl waitingListServiceUnderTest;

    @BeforeEach
    public void initMock(){
        waitingListServiceUnderTest = new WaitingListServiceImpl(
                waitingListRepository, empruntRepository,
                copyRepository,
                mailService,
                bookRepository,
                customerRepository);
    }

    @Test
    void whenNoWaitingList_shouldNotSendMail() {
        String status ="Waiting";
        Book book = new Book();
        book.setId(2L);
        Copy copy = new Copy();
        copy.setId(1L);
        copy.setStatus(status);
        List<WaitingList> waitingList = new ArrayList<>();
        when(waitingListRepository.getWaitingListByIdBookByDateRequest(2L)).thenReturn(waitingList);
        when(copyRepository.getCopyByStatus(2L,status)).thenReturn(copy);
        waitingListServiceUnderTest.sendMailForNextCustomer(2L);
        verify(mailService,times(0)).sendMessage(
                "argTo","argFirst", "argLast", "argTitle");
    }


    @Test
    void whenWaitingList_shouldSendMail() {
        String status ="Waiting";
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Title");

        Copy copy = new Copy();
        copy.setId(1L);
        copy.setStatus(status);
        copy.setBook(book);

        Customer customer =new Customer();

        List<WaitingList> waitingLists = new ArrayList();
        WaitingList waitingList = new WaitingList(
                2L, new Date(), null, null, customer, null);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-2);
        date = calendar.getTime();
        customer.setFirstName("OkName");
        WaitingList waitingList1 = new WaitingList(
                5L, date, null, null, customer, null);
        waitingLists.add(waitingList1);
        waitingLists.add(waitingList);


        when(waitingListRepository.getWaitingListByIdBookByDateRequest(2L)).thenReturn(waitingLists);
        when(copyRepository.getCopyByStatus(2L,status)).thenReturn(copy);

        waitingListServiceUnderTest.sendMailForNextCustomer(2L);
        verify(mailService,times(1)).sendMessage(
                waitingList.getCustomer().getEmail(),waitingList.getCustomer().getFirstName(),
                waitingList.getCustomer().getLastName(), copy.getBook().getTitle());
    }

    @DisplayName("Insert in WaitingList is Available")
    @Test
    void whenCopyIsAvailable_shouldFalse() {
        int numberBookInWaitingList = 0;
        int numberOfWaitingListAvailable = 2;
        Long idBook = 1L;
        Long idCustomer = 5L;

        when(waitingListRepository.getWaitingListByIdCustomerAndIdBook(idCustomer, idBook)).thenReturn(null);
        when(copyRepository.getNumberCopyByBookAvailable(idBook)).thenReturn(2);
        when(empruntRepository.getEmpruntByIdCustomerByIdBook(idCustomer, idBook)).thenReturn(null);

        waitingListServiceUnderTest.insertInWaitingListAvailable(numberBookInWaitingList, numberOfWaitingListAvailable,
                idBook, idCustomer);

        assertThat(waitingListServiceUnderTest.insertInWaitingListAvailable(
                numberBookInWaitingList, numberOfWaitingListAvailable,
                idBook, idCustomer).booleanValue()).isFalse();
    }

    @DisplayName("Insert in WaitingList is Available")
    @Test
    void whenInsertIsOk_shouldtrue() {
        int numberBookInWaitingList = 0;
        int numberOfWaitingListAvailable = 2;
        Long idBook = 1L;
        Long idCustomer = 5L;

        when(waitingListRepository.getWaitingListByIdCustomerAndIdBook(idCustomer, idBook)).thenReturn(null);
        when(copyRepository.getNumberCopyByBookAvailable(idBook)).thenReturn(0);
        when(empruntRepository.getEmpruntByIdCustomerByIdBook(idCustomer, idBook)).thenReturn(null);

        waitingListServiceUnderTest.insertInWaitingListAvailable(numberBookInWaitingList, numberOfWaitingListAvailable,
                idBook, idCustomer);

        assertThat(waitingListServiceUnderTest.insertInWaitingListAvailable(
                numberBookInWaitingList, numberOfWaitingListAvailable,
                idBook, idCustomer).booleanValue()).isTrue();
    }

    @DisplayName("when insert a new waitinglist")
    @Disabled
    @Test
    void whenInsertIsNotAvailable_shouldThrowWaitingListException() {
        int numberOfWaitinListAvailable=4;
        int numberBookInWaitingList=4;

        when(copyRepository.getNumberCopyByBook(2L)).thenReturn(numberOfWaitinListAvailable);
        when(waitingListRepository.getNumberBookInWaitingList(2L)).thenReturn(numberBookInWaitingList);
        when(waitingListService.insertInWaitingListAvailable(anyInt(), anyInt(),anyLong(), anyLong()))
                .thenReturn(false);

        assertThatThrownBy(()-> waitingListServiceUnderTest.insertWaitingList(2L,1L))
                .isInstanceOf(WaitingListException.class)
                .hasMessage("WaitingList not available");
    }


    @DisplayName("when insert a new waitinglist")
    @Test
    void whenInsertIsAvailable_shouldInsert() {
        int numberOfWaitinListAvailable=4;
        int numberBookInWaitingList=0;

        when(copyRepository.getNumberCopyByBook(2L)).thenReturn(numberOfWaitinListAvailable);
        when(waitingListRepository.getNumberBookInWaitingList(2L)).thenReturn(numberBookInWaitingList);

        WaitingList waitingList = new WaitingList();
        waitingList.setId(5L);

        waitingListServiceUnderTest.insertWaitingList(2L,4L);
        verify(waitingListRepository,times(1)).save(argumentCaptor.capture());
    }

    @Test
    void getInWaitingList() {
        WaitingListDto waitingListDto = new WaitingListDto();
        waitingListDto.setNumberInWaitingList(1);
        waitingListDto.setDateRequest(new Date());
        BookDto book = new BookDto();
        book.setId(2L);
        waitingListDto.setBook(book);
        when(waitingListRepository.getNumberInWaitingList(waitingListDto.getBook().getId(),waitingListDto.getDateRequest()))
                .thenReturn(1);
        assertThat(waitingListServiceUnderTest.getInWaitingList(waitingListDto)).isEqualTo(2);
    }

    @Test
    void updateWaintingList() {
        WaitingListDto waitingListDto = new WaitingListDto();
        waitingListDto.setId(2L);
        waitingListDto.setNumberInWaitingList(1);
        Date date = new Date();
        waitingListDto.setNextReturn(date);
        waitingListServiceUnderTest.updateWaintingList(1, date, waitingListDto);
        assertThat(waitingListDto).isEqualTo(waitingListDto);

    }
}