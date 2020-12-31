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
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    public WaitingList() {
    }

    public WaitingList(final Long id, final Date dateRequest, final Copy copy, final Customer customer, final Library library) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.copy = copy;
        this.customer = customer;
        this.library = library;
    }

    @Override
    public String toString() {
        return "WaitingList{" +
                "id=" + id +
                ", dateRequest=" + dateRequest +
                ", copy=" + copy +
                ", customer=" + customer +
                ", library=" + library +
                '}';
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

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(final Copy copy) {
        this.copy = copy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}
