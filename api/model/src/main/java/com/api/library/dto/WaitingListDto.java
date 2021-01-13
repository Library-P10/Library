package com.api.library.dto;

import java.util.Date;

public class WaitingListDto {

    private Long id;
    private Date dateRequest;
    private Date dateSendingMail;
    private Date dateRecoveryLimit;
    private CustomerDto customer;
    private BookDto book;
    private int numberInWaitingList;

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

    public Date getDateSendingMail() {
        return dateSendingMail;
    }

    public void setDateSendingMail(final Date dateSendingMail) {
        this.dateSendingMail = dateSendingMail;
    }

    public Date getDateRecoveryLimit() {
        return dateRecoveryLimit;
    }

    public void setDateRecoveryLimit(final Date dateRecoveryLimit) {
        this.dateRecoveryLimit = dateRecoveryLimit;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDto customer) {
        this.customer = customer;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(final BookDto book) {
        this.book = book;
    }

    public int getNumberInWaitingList() {
        return numberInWaitingList;
    }

    public void setNumberInWaitingList(final int numberInWaitingList) {
        this.numberInWaitingList = numberInWaitingList;
    }
}
