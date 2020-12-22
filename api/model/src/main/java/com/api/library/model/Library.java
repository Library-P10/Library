package com.api.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library")
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "library")
    private List<Copy> copyList = new ArrayList<>();

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(final String adress) {
        this.adress = adress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(final String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Copy> getCopyList() {
        return copyList;
    }

    public void setCopyList(final List<Copy> copyList) {
        this.copyList = copyList;
    }
}