package com.api.library.mapper;

import com.api.library.dto.CustomerDto;
import com.api.library.model.Customer;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-21T23:01:57+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
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

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
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
}
