package com.api.library.service.impl;

import com.api.library.repository.WaitingListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WaitingListServiceImplTest {

    @Mock WaitingListRepository waitingListRepository;

    @InjectMocks WaitingListServiceImpl waitingListServiceUnderTest;

    @Test
    void getWaitingListByIdBookByDateRequest() {
    }

    @Test
    void getNumberBookInWaitingList() {
    }

    @Test
    void deleteWaitingList() {
    }

    @Test
    void sendMailForNextCustomer() {
    }

    @Test
    void getWaitingListById() {
    }

    @Test
    void getWaitingListByDateRecoveryLimitExceeded() {
    }

    @Test
    void insertInWaitingListAvailable() {
    }

    @Test
    void getWaitingListByIdCustomerAndIdBook() {
    }

    @Test
    void insertWaitingList() {
    }

    @Test
    void getNumberCustomerInWaitingList() {
    }

    @Test
    void getWaitingListByIdCustomer() {
    }

    @Test
    void getInWaitingList() {
    }

    @Test
    void updateWaintingList() {
    }
}