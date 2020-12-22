package com.api.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emprunt")
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "emprunt_date")
    private Date empruntDate = new Date();

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "is_extended")
    private Boolean isExtended;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", empruntDate=" + empruntDate +
                ", returnDate=" + returnDate +
                ", isExtended=" + isExtended +
                ", copy=" + copy +
                ", customer=" + customer +
                '}';
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getEmpruntDate() {
        return empruntDate;
    }

    public void setEmpruntDate(final Date empruntDate) {
        this.empruntDate = empruntDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final Date returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getExtended() {
        return isExtended;
    }

    public void setExtended(final Boolean extended) {
        isExtended = extended;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(final Copy copy) {
        this.copy = copy;
    }

}