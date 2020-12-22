package com.api.library.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pub_date")
    private Date pubDate;

    @Column(name = "page")
    private int page;

    @Column(name = "synopsis")
    private String synopsis;

    //TODO Image à gérer
    @Column(name = "cover")
    private String cover;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubDate=" + pubDate +
                ", page=" + page +
                ", synopsis='" + synopsis + '\'' +
                ", cover='" + cover + '\'' +
                ", categorie=" + categorie +
                ", author=" + author +
                '}';
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(final Categorie categorie) {
        this.categorie = categorie;
    }

    public Book(final Long id, final String title, final Date pubDate, final int page, final String subject, final String synopsis, final String cover)
    {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.page = page;
        this.synopsis = synopsis;
        this.cover = cover;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public Date getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(final Date pubDate)
    {
        this.pubDate = pubDate;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(final int page)
    {
        this.page = page;
    }

    public String getSynopsis()
    {
        return synopsis;
    }

    public void setSynopsis(final String synopsis)
    {
        this.synopsis = synopsis;
    }

    public String getCover()
    {
        return cover;
    }

    public void setCover(final String cover)
    {
        this.cover = cover;
    }
}