package com.api.library.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "waitingList")
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "dateRequest")
    private Date dateRequest;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Override
    public String toString() {
        return "WaitingList{" +
                "id=" + id +
                ", dateRequest=" + dateRequest +
                ", customer=" + customer +
                ", book=" + book +
                '}';
    }

    public WaitingList() {
    }

    public WaitingList(final Long id, final Date dateRequest, final Customer customer, final Book book) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.customer = customer;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}
