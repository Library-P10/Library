package com.web.library.batchlibrary.model;

import java.util.Date;

public class WaitingList {

    private Long id;
    private Date dateRequest;
    private Date dateSendingMail;
    private Customer customer;
    private Book book;

    public WaitingList() {
    }

    public WaitingList(final Long id, final Date dateRequest,
                       final Date dateSendingMail, final Customer customer,
                       final Book book) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.dateSendingMail = dateSendingMail;
        this.customer = customer;
        this.book = book;
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }
}
