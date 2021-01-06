package com.api.library.repository;

import com.api.library.model.Book;
import com.api.library.model.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

    // Récupère la liste d'attente d'un book
    @Query("SELECT w FROM WaitingList w WHERE w.book.id = :book ORDER BY w.dateRequest asc ")
    List<WaitingList> getWaitingListByIdBook(@Param("idBook") Long idbook);

    // Récupère le nombre d'occurence d'un livre dans la liste d'attente
    @Query("SELECT COUNT (w.book.id) AS numberOfBook FROM WaitingList w WHERE w.book.id = :idBook")
    Integer getNumberBookInWaitingList(@Param("idBook") Long idBook);
}
