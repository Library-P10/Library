package com.api.library.repository;

import com.api.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Récupère la liste des books
    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    // Récupère le livre selon la catégorie
    @Query("SELECT b FROM Book b WHERE b.categorie.label = :categorie")
    List<Book> getBookByCategorie(@Param("categorie") String categorie);

    // Récupère les livres selon la saisie
    @Query("SELECT b FROM Book b WHERE (b.title LIKE :saisie) OR (b.author.lastName LIKE :saisie) OR (b.author.firstName LIKE :saisie)")
    List<Book> getBookBySearch(@Param("saisie")String saisie);

    //Récupère le livre selon l'id
    @Query("SELECT b FROM Book b WHERE b.id = :idBook")
    Book getBookById(@Param("idBook")Long idBook);

}
