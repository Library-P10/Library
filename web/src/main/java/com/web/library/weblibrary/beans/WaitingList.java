package com.web.library.weblibrary.beans;

import java.util.Date;

public class WaitingList {

    private Long id;
    private Date dateRequest;
    private Date dateSendingMail;
    private Date dateRecoveryLimit;
    private Book book;

    @Override
    public String toString() {
        return "WaitingList{" +
                "id=" + id +
                ", dateRequest=" + dateRequest +
                ", dateSendingMail=" + dateSendingMail +
                ", dateRecoveryLimit=" + dateRecoveryLimit +
                ", book=" + book +
                '}';
    }

    public WaitingList() {
    }

    public WaitingList(final Long id,
                       final Date dateRequest,
                       final Date dateSendingMail,
                       final Date dateRecoveryLimit,
                       final Book book) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.dateSendingMail = dateSendingMail;
        this.dateRecoveryLimit = dateRecoveryLimit;
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

    public Date getDateRecoveryLimit() {
        return dateRecoveryLimit;
    }

    public void setDateRecoveryLimit(final Date dateRecoveryLimit) {
        this.dateRecoveryLimit = dateRecoveryLimit;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }
}
