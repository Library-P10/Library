package com.api.library.mapper;

import com.api.library.dto.CustomerDto;
import com.api.library.dto.WaitingListDto;
import com.api.library.model.Customer;
import com.api.library.model.WaitingList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T12:18:26+0100",
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
        waitingList.setDateRequest( waitingListDto.getDateRequest() );
        waitingList.setDateSendingMail( waitingListDto.getDateSendingMail() );

        return waitingList;
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
}
