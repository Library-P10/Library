package com.api.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "waiting_list")
public class WaitingList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "date_request")
    private Date dateRequest;

    @Column(name = "date_sending_mail")
    private Date dateSendingMail;

    @Column(name="date_recovery_limit")
    private Date dateRecoveryLimit;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public WaitingList() {
    }

    public WaitingList(final Long id, final Date dateRequest, final Date dateSendingMail,
                       final Date dateRecoveryLimit, final Customer customer, final Book book) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.dateSendingMail = dateSendingMail;
        this.dateRecoveryLimit = dateRecoveryLimit;
        this.customer = customer;
        this.book = book;
    }



    @Override
    public String toString() {
        return "WaitingList{" +
                "id=" + id +
                ", dateRequest=" + dateRequest +
                ", dateSendingMail=" + dateSendingMail +
                ", dateRecoveryLimit=" + dateRecoveryLimit +
                ", customer=" + customer +
                ", book=" + book +
                '}';
    }

    public void setCustomer(final com.api.library.model.Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
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
}
