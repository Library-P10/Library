package com.api.library.dto;

import java.util.Date;

public class WaitingListDto {

    private Long id;
    private Date dateRequest;
    private CustomerDto customer;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(final Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDto customer) {
        this.customer = customer;
    }
}
