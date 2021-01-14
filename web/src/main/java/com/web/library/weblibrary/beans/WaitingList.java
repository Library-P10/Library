package com.web.library.weblibrary.beans;

import java.util.Date;

public class WaitingList {

    private Long id;
    private Date dateRequest;
    private Date dateSendingMail;
    private Date dateRecoveryLimit;
    private int numberInWaitingList;
    private Date nextReturn;
    private Book book;

    @Override
    public String toString() {
        return "WaitingList{" +
                "id=" + id +
                ", dateRequest=" + dateRequest +
                ", dateSendingMail=" + dateSendingMail +
                ", dateRecoveryLimit=" + dateRecoveryLimit +
                ", numberInWaitingList=" + numberInWaitingList +
                ", nextReturn=" + nextReturn +
                ", book=" + book +
                '}';
    }

    public WaitingList() {
    }

    public WaitingList(final Long id, final Date dateRequest,
                       final Date dateSendingMail, final Date dateRecoveryLimit,
                       final int numberInWaitingList, final Date nextReturn,
                       final Book book) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.dateSendingMail = dateSendingMail;
        this.dateRecoveryLimit = dateRecoveryLimit;
        this.numberInWaitingList = numberInWaitingList;
        this.nextReturn = nextReturn;
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

    public int getNumberInWaitingList() {
        return numberInWaitingList;
    }

    public void setNumberInWaitingList(final int numberInWaitingList) {
        this.numberInWaitingList = numberInWaitingList;
    }

    public Date getNextReturn() {
        return nextReturn;
    }

    public void setNextReturn(final Date nextReturn) {
        this.nextReturn = nextReturn;
    }
}
