package com.api.library.dto;

import java.util.Date;

public class EmpruntDto {

    private Long id;
    private Date empruntDate;
    private Date returnDate;
    private Boolean isExtended;
    private CopyDto copy;
    private CustomerDto customer;

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

    public CopyDto getCopy() {
        return copy;
    }

    public void setCopy(final CopyDto copy) {
        this.copy = copy;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDto customer) {
        this.customer = customer;
    }
}
