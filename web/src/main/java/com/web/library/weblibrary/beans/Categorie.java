package com.web.library.weblibrary.beans;

public class Categorie {

    private Long id;
    private String label;

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }

    public Categorie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }
}
