package com.api.library.service.impl;

import com.api.library.dto.CustomerDto;
import com.api.library.mapper.CustomerMapper;
import com.api.library.model.Customer;
import com.api.library.model.WaitingList;
import com.api.library.repository.CustomerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock CustomerRepository customerRepository;
    @Mock BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private CustomerServiceImpl customerServiceUnderTest;

    private ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);

    @Test
    void saveCustomer() {
        CustomerDto customer = new CustomerDto();
        customer.setId(2L);
        customer.setPassword("toto");
        String hashPw=bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(hashPw);

        customerServiceUnderTest.saveCustomer(customer);
        verify(customerRepository,times(1)).save(argumentCaptor.capture());
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(2L);
        customerDto.setFirstName("BeforeTestFirstName");
        customerDto.setLastName("BeforeTestLastName");

        customerServiceUnderTest.updateCustomer(customerDto);
        verify(customerRepository,times(1)).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getId()).isEqualTo(customerDto.getId());
        assertThat(argumentCaptor.getValue().getFirstName()).isEqualTo(customerDto.getFirstName());
        assertThat(argumentCaptor.getValue().getLastName()).isEqualTo(customerDto.getLastName());
    }

    @Test
    void updatePassword() {
        Long idCustomer = 1L;
        String password = "password";

        when(bCryptPasswordEncoder.encode(password)).thenReturn(
                "$2a$10$AoKHZdtNnmqcb9B0J588m.WXJGUql3J38KYVYSdJ/VbxnRWWiIy2.");

        customerServiceUnderTest.updatePassword(idCustomer, password);

        verify(customerRepository,times(1))
                .updatePassword(idCustomer,
                        "$2a$10$AoKHZdtNnmqcb9B0J588m.WXJGUql3J38KYVYSdJ/VbxnRWWiIy2.");
    }
}